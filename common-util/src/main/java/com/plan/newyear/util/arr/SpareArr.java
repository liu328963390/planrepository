package com.plan.newyear.util.arr;


/**
 * 数据结构之 稀疏数组
 */
public class SpareArr {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int cheseArr1[][]  = new int[11][11];
        cheseArr1[1][2] = 1;
        cheseArr1[2][4] = 2;
        cheseArr1[5][8] = 1;
        //输出原始的二维数组
        for (int [] row :cheseArr1) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转稀疏数组的思路
        //1。先遍历二维数组得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <11 ; j++) {
                if (cheseArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组，将非0的值存放到sparseArr中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <11 ; j++) {
                if (cheseArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] =i;
                    sparseArr[count][1] =j;
                    sparseArr[count][2] =cheseArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);//格式化输出制表符
        }
        System.out.println();

        //将稀疏数组恢复成原始的二维数组
        //1.先读取第一行，根据第一行的数据，创建原始的二维数组
        int cheseArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2。在读取稀疏数组后几行的数据（从第二行开始），并赋值给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            cheseArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出恢复后的二维数组
        for (int [] row :cheseArr2) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
