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

    //获取 队列的数据，出队列
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
