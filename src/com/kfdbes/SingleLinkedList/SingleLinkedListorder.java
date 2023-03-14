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
