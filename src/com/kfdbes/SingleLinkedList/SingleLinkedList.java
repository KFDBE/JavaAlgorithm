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