# JavaAlgorithm
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

```

