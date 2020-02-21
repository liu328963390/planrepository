package com.plan.newyear.util.algorithm;

/**
 * 动态规划算法：
 */
public class DynamicKnapsack{
    public static void main(String[] args) {
        int[] weight={1,4,3,2,5};//商品的重量
        int[] value = {1500,3000,2000,1000,5000};//商品的价值
        int maxSize = 8;//背包的容量
        int n = value.length;//物品的个数
        //记录放入商品的情况，定义一个二维数组
        int[][] temp = new int[n+1][maxSize+1];
        //创建二维数组，代表表
        //v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] vw = new int[n+1][maxSize+1];
        //初始化第一行和第一列，默认为0
        for (int i = 0; i < vw.length; i++) {
            vw[i][0] = 0;//将第一列设置为0
        }
        for (int i = 0; i < vw[0].length; i++) {
            vw[0][i] = 0;//将第一行设置0
        }

        //动态规划来处理
        for (int i = 1; i < vw.length; i++) {//不处理第一行
            for (int j = 1; j < vw[0].length; j++) {//不处理第一列
                //公式
                if (weight[i-1]>j){//因为我们的程序i是从1开始的，因此原来公式中的w[i]修改成w[i-1]
                    vw[i][j]=vw[i-1][j];
                }else {
                    //说明：这里的i从1开始，因此公式需要调整vw[i][j]=Math.max(vw[i-1][j],value[i-1]+vw[i-1][j-weight[i-1]]);
                    vw[i][j]=Math.max(vw[i-1][j],value[i-1]+vw[i-1][j-weight[i-1]]);
                    //为了商品存入背包的情况，不能直接使用上面的公式，使用if else来体现
                    if (vw[i-1][j]<value[i-1]+vw[i-1][j-weight[i-1]]){
                        vw[i][j]=value[i-1]+vw[i-1][j-weight[i-1]];
                        //把当前的情况记录下来
                        temp[i][j] = 1;
                    }else {
                        vw[i][j]=vw[i-1][j];
                    }
                }
            }
        }
        //输出
        for (int i = 0; i < vw.length; i++) {
            for (int j = 0; j < vw[i].length; j++) {
                System.out.print(vw[i][j]+"    ");
            }
            System.out.println();
        }
        //输出最后我们存入的哪些商品
//        for (int i = 0; i < temp.length; i++) {
//            for (int j = 0; j < temp[i].length; j++) {
//                if (temp[i][j] ==1){
//                    System.out.printf("第%d个商品放入到背包\n",i);
//                }
//            }
//        }

        //逆向遍历，从temp数组的最后开始找
        int i =temp.length-1;//行的最大下标
        int j = temp[0].length-1;//列的最大下标
        while (i>0 && j>0){
            if (temp[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j -= weight[i-1];//w[i]这个重量
            }
            i--;
        }
    }
}
