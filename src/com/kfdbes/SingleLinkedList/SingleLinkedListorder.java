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
