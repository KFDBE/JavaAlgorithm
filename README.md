# JavaAlgorithm
## 线性结构和非线性结构
数据结构包括线性结构和非线性结构。
### 线性结构
1. 线性结构作为最常用的数据结构，其特点是数据元素之间存在一对一的线性关系
2. 线性结构有两种不同的存储结构，即顺序结构和链式存储结构，顺序存储的线性表成为顺序表，顺序表中的存储元素是连续的
3. 链式存储的线性表成为链表，链表中的存储的元素不一定是连续的，元素节点存放的数据元素以及相邻元素的地址信息
4. 线性结构常见的有：数组，队列，链表和栈
### 非线性结构
非线性结构包括：二维数组，多维数组，广义表，数结构，图结构
## 稀疏数组
### 稀疏数组的转换思路
**二维数组转稀疏数组的思路**

1. 遍历原始的二维数组，得到有效的个数sum
2. 根据sum就可以创建稀疏数组sparseArr  int[sum + 1] [3]
3. 将二维数组的有效数据存入到稀疏数组中

**稀疏数组转原始的二维数组的思路**
1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二位数组
2. 在读取稀疏数组后几行的数据，并赋给原始的二维数组

### 稀疏数组实现的代码

```java
package com.kfdbes.algorithm;

public class Algorithm {
    public static void main (String[] args){
        //创建一个原始的二维数组11*11
        //0: 表示没有棋子 1：表示黑子 2：表示白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        for(int[] row : chessArr1){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转稀疏数组的思路
        //先遍历二维数组得到非0数据的个数
        int sum = 0;
        for(int i = 0 ; i < 11 ; i++){
            for (int j = 0 ; j < 11 ; j++){
                if(chessArr1[i][j] != 0 ){
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);

        //2. 创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到sparseArr
        int count = 0;
        for (int i = 0 ; i < 11 ; i++){
            for (int j = 0;j < 11; j++){
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为------");
        for (int i = 0; i < sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
            System.out.println();
        }
        //将稀疏数组 --》恢复成原始的二维数组
        /*
        * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2 = int[11][11]
        * 2. 在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
        * */

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        for(int i = 1 ; i < sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println();
        System.out.println("恢复后的二维数组");

        for (int[] row : chessArr2){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
```

![image-20230310195141298](/home/kfdbes/.config/Typora/typora-user-images/image-20230310195141298.png)

![image-20230310195240929](/home/kfdbes/.config/Typora/typora-user-images/image-20230310195240929.png)

## 数组模拟对列

队列本身是有序列表，诺使用数组的结构来存储队列的数据，则队列数组的声明如下图，其中maxsize是该队列的最大容量。

因为队列的输出、输入是分别从前后端来处理的，因此需要两个变量front及rear分别记录队列前后端的下标，front会随着数据的输出而改变，而rear则是随着数据的输入而改变。

当我们将数据存入到队列时称为“addQueue“，addQueue的处理需要两个步骤，思路如下：

1. 将尾指针往后移：rear+1，当front == rear[空]

2. 若尾指针rear小于队列的最大下标maxsize-1，则将数据存入到rear所指的数组元素中，否则无法存入到数据中，否则无法存入数据。rear == maxsize - 1[队列满]

### 数组模拟队列实现代码

```java
package com.kfdbes.queue;

import java.util.Scanner;

public class Queue {
    public static void main(String[] args){
        //测试
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show) : 显示队列");
            System.out.println("e(exit) : 退出队列");
            System.out.println("a(add) : 添加数据队列");
            System.out.println("g(get) : 从队列取出数据");
            System.out.println("h(head) : 查看队列头的数据");
            key = scanner.next().charAt(0); //
            switch (key){
                case's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据: ");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g'://取出数据
                    try{
                        int res = arrayQueue.getQueue();
                        System.out.println("取出的数据是%d\n" + res);
                    }catch (Exception e){
                        System.out.println("队列为空");
                    }break;
                case 'h':
                    arrayQueue.headQueue();
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
            }
        }
    }

}
class ArrayQueue{
    private int maxsize;//表示数组的最大容量
    private int front;// 队列头
    private int rear;// 队列尾
    private int[] arr; // 该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxsize = arrMaxSize;
        arr = new int[maxsize];
        front = -1;//只想队列头部，分析出front是指向队列头的前一个位置
        rear = -1; //指向队列尾，指向队列尾的数据（即就是队列最后一个数据）
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxsize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据--");
            return;
        }
        arr[rear++] = n ;//让rear后移
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能读取数据--");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空的，没有数据～～");
        }
        for(int i = 0;i < arr.length;i++){
            System.out.println("arr[%d] = %d\n" + i + arr[i]);
        }

    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        //判断
        if(isEmpty()){
            throw new RuntimeException("队列空的，没有数据～～");
        }
        return arr[front + 1];
    }

}
```

### 问题分析并优化
1. 目前数组使用一次就不使用，没有达到服用的效果
2. 将这个数组使用算法，改进成一个环形的数组

## 数组模拟环形队列
思路如下：
1. front 变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的一个元素front的初始值为0
2. rear 变量的含义做一个调整：rear就指向队列的最后一个元素，因为希望空出一个位置作为约定rear的初始值为0
3. 当队列满的时候，条件是（rear + 1）% maxsize = front   [满]
4. 当队列为空的条件，rear == front   [空]
5. 队列中有效数据的个数（rear + maxSize -front）%maxsize

```java
package com.kfdbes.ArraySimulation;


import java.util.Scanner;

public class CircleArrayQueue {

    public static void main(String[] args) {
        //测试一把
        CircleArray arrayQueue = new CircleArray(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show) : 显示队列");
            System.out.println("e(exit) : 退出队列");
            System.out.println("a(add) : 添加数据队列");
            System.out.println("g(get) : 从队列取出数据");
            System.out.println("h(head) : 查看队列头的数据");
            key = scanner.next().charAt(0); //
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据: ");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g'://取出数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println("取出的数据是%d\n" + res);
                    } catch (Exception e) {
                        System.out.println("队列为空");
                    }
                    break;
                case 'h':
                    arrayQueue.headQueue();
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
            }

        }

    }

    static class CircleArray {
        private int maxsize;//表现数组的最大容量

        private int[] arr;//该数据用于存放数据，模拟队列
        //front变量的含义做了一个调整，front就指向队列的第一个元素，也就是说arr[front]
        //front 的初始值为0
        private int front;
        //rear 变量的含义做一个调整：rear就指向队列的最后一个元素，因为希望空出一个位置作为约定rear的初始值为0
        private int rear;//队列尾

        public CircleArray(int arrMaxSize) {
            maxsize = arrMaxSize;
            arr = new int[maxsize];
            front = 0;
            rear = 0;
        }

        //判断是否满
        public boolean isFull() {
            return (rear + 1) % maxsize == front;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public void addQueue(int n) {
            if (isFull()) {
                System.out.println("队列已满");
                return;
            }
            arr[rear] = n;
            //将rear后移
            rear = (rear + 1) % maxsize;
            System.out.println("添加成功");
        }

        //取出数据
        public int getQueue() {
            //判断队列是否为空
            if (isEmpty()) {
                //通过抛出异常
                throw new RuntimeException("队列为空,不能取出数据");
            }
            //这里需要分析出front是指向队列的第一个元素
            //1. 先把front对应的值保留到一个临时变量
            //2. 将front后移,考虑取魔
            //3. 将临时保存到的变量返回
            int temp = arr[front];
            front = (front + 1) % maxsize;
            return temp;
        }

        //显示队列的数据
        public void showQueue() {
            //遍历
            if (isEmpty()) {
                System.out.println("队列空的，没有数据～～");
            }
            //思路：从front开始遍历，遍历多少个元素
            for (int i = 0; i < front + size(); i++) {
                System.out.println("arr[%d] = %d\n" + i % maxsize + arr[i % maxsize]);
            }
        }

        //求出当前队列有效数据的个数
        public int size() {
            return (rear + maxsize - front) % maxsize;
        }

        //显示队列的头数据，注意不是取出数据
        public int headQueue() {
            //判断
            if (isEmpty()) {
                throw new RuntimeException("队列空的，没有数据～～");
            }
            return arr[front];
        }
    }
}
```

## 链表
链表是有序链表，但是它在内存当中存储是不连续的。
1. 链表是以节点的方式来存储。
2. 每个节点包含data域，，next域：指向下一个节点
3. 发现链表的各个节点不一定是连续存放的
4. 链表分带头节点的链表和没有头节点的链表根据实际需求来决定

## 单链表
### 添加、删除、修改数据
思路如下：
1. 先创建一个head头节点，作用就是表示单链表的头
2. 后面我们每添加一个节点，就直接加入到链表的在最后
3. 遍历时通过一个辅助变量，帮助我们遍历整个链表

```java
package com.kfdbes.SingleLinkedList;

public class SingleLinkedList {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");

        //创建一个链表
        LinkedList list = new LinkedList();
        list.add(hero1);
        list.add(hero2);
        list.add(hero3);

        //显示链表
        list.list();

    }
}


//定义一个SingleLinkedList,管理我们的英雄
class LinkedList {
    //先初始化一个头节点,头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表
    public void add(HeroNode node){
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        temp.next = node;
    }

    //显示链表
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //遍历链表
        HeroNode temp = head.next;
        while (temp!= null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

}



class HeroNode {
    //先定义一个HeroNode,每个HeroNode,对象就是一个节点
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int hno, String hname, String hnickname){
        this.no = hno;
        this.name = hname;
        this.nickname = hnickname;
    }
    //为了显示方便，我们重新定义toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}
```

```java
package com.kfdbes.SingleLinkedList;

public class SingleLinkedListorder {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");

        //创建一个链表
        LinkedListorder list = new LinkedListorder();
        list.addByOrder(hero3);
        list.addByOrder(hero2);
        list.addByOrder(hero1);

        //测试修改
        HeroNode newHero = new HeroNode(2,"飞舞","KFDBES");
        list.update(newHero);
        System.out.println("-----------");
        list.list();

        //删除一个节点
        list.del(1);
        list.list();
    }
}


//定义一个SingleLinkedList,管理我们的英雄
class LinkedListorder {
    //先初始化一个头节点,头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表
    public void add(HeroNode node){
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        temp.next = node;
    }

    //第二种添加英雄顺序的方式
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加位置
         HeroNode temp = head;
         boolean flag = false;//标志添加的编号是否存在，默认为flase
        //遍历链表，找到最后
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > heroNode.no){
                break;
            }
            else if (temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
            //如果没有找到最后，将temp后移
        }
        if (flag){
            System.out.println("准备插入的英雄已存在");
        }
        else{
            System.out.println("准备插入的英雄不存在");
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息
    //说明
    //1. 根据newHerNode的no来修改
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //遍历链表
        HeroNode temp = head.next;
        boolean flag = false;
        while (temp!= null){
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            System.out.println("找到要修改的节点");
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }
        else{
            System.out.println("找不到要修改的节点");
        }
    }

    //删除节点
    //思路
    //1. head不能动，因此我们需要一个temp辅助节点找到要删除的节点的前一个节点
    //2. 说明我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点的
        while(true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else{
             System.out.println("找不到要删除的节点");
        }

    }
    //显示链表
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //遍历链表
        HeroNode temp = head.next;
        while (temp!= null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

}
```

```java
package com.kfdbes.SingleLinkedList;

public class SingleLinkedListorder {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");

        //创建一个链表
        LinkedListorder list = new LinkedListorder();
        list.addByOrder(hero3);
        list.addByOrder(hero2);
        list.addByOrder(hero1);

        //测试修改
        HeroNode newHero = new HeroNode(2,"飞舞","KFDBES");
        list.update(newHero);
        System.out.println("-----------");
        list.list();

        //删除一个节点
        list.del(1);
        list.list();
    }
}


//定义一个SingleLinkedList,管理我们的英雄
class LinkedListorder {
    //先初始化一个头节点,头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HeroNode node){
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        temp.next = node;
    }

    //第二种添加英雄顺序的方式
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加位置
         HeroNode temp = head;
         boolean flag = false;//标志添加的编号是否存在，默认为flase
        //遍历链表，找到最后
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > heroNode.no){
                break;
            }
            else if (temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
            //如果没有找到最后，将temp后移
        }
        if (flag){
            System.out.println("准备插入的英雄已存在");
        }
        else{
            System.out.println("准备插入的英雄不存在");
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息
    //说明
    //1. 根据newHerNode的no来修改
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //遍历链表
        HeroNode temp = head.next;
        boolean flag = false;
        while (temp!= null){
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            System.out.println("找到要修改的节点");
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }
        else{
            System.out.println("找不到要修改的节点");
        }
    }

    //删除节点
    //思路
    //1. head不能动，因此我们需要一个temp辅助节点找到要删除的节点的前一个节点
    //2. 说明我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点的
        while(true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else{
             System.out.println("找不到要删除的节点");
        }

    }

    //方法：获取单链表的节点个数
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode temp = head.next;
        while (temp!= null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找单链表中的倒数第K个节点
    //思路
    //编写一个方法，结婚搜head节点，同时接受一个index
    //index表示是倒数第index个节点
    //先把链表从头到尾进行遍历得到链表总的长度
    //得到size后，在遍历size-K
    public static HeroNode FindLastIndexNode(HeroNode head,int index){
        if(head.next == null){
            return null;
        }
        int size = getLength(head);
        //第二次遍历size-index位置，就是我们倒数的第K个节点
        //先做一个index的校厌
        if(index <= 0 || index > size){
            return null;
        }
        //定义一个辅助变量
        HeroNode temp = head.next;
        for (int i = 0 ; i< size-index;i++){
            temp = temp.next;
        }
        return temp;
    }

    //单链表的反转
    //先定义一个节点，reverseHead = new HeroNOde();
    //从头到尾遍历原来的链表，每遍历一个节点，将其取出，放到新的链表的最前端
    public void reversesetList(HeroNode head){
        if (head.next == null || head.next.next ==null){
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;//定义当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表
        while (cur!= null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead;
    }


    //显示链表
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //遍历链表
        HeroNode temp = head.next;
        while (temp!= null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

}
```

## 双向链表
