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
2. reart 变量的含义做一个调整：rear就指向队列的最后一个元素，因为希望空出一个位置作为约定rear的初始值为0
3. 当队列满的时候，条件是（rear + 1）% maxsize = front   [满]
4. 当队列为空的条件，rear == front   [空]
5. 队列中有效数据的个数（rear + maxSize -front）%maxsize
