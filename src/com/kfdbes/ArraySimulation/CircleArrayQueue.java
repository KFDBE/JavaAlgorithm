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