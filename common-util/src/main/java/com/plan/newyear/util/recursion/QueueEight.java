package com.plan.newyear.util.recursion;

/**
 * 八皇后问题：使用递归解决
 */
public class QueueEight {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array,保存皇后旋转位置的结果，array={0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    static  int judgeCount = 0;
    public static void main(String[] args) {
        //测试
        QueueEight eight = new QueueEight();
        eight.check(0);
        System.out.printf("一共有%d解法\n",count);
        System.out.printf("一共调用judge方法有%d次\n",judgeCount);
    }

    //放置第n个皇后
    //注意：check是每一次递归时，进入到check中都有for (int i = 0; i <max ; i++)循环，因此，会有回溯
    private void check(int n){
        if (n == max){//n==8,其实8个皇后就已经放好
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i <max ; i++) {
            //先把当前皇后n，放到该行的第1列
            array[n] = i;
            //判断 当放置第n个皇后到i列时，是否冲突
            if (judge(n)){//不冲突
                //接着放n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行array[n]=i;将第n个皇后，放置在本行的后移的一个位置

        }
    }
    //查看当我们放置第n个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i <n ; i++) {
            //array[i] == array[n] 表示第n个皇后是否和前面的n-1个皇后在同一列
            //Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示第n个皇后是否和第i个皇后在同一斜线上
            //n=1 代表第2个皇后，放在第2列1 n=1 array[1] =1
            //Math.abs(1-0) ==1 Math.abs(array[n]-array[i])=Math.abs(1-0)==1
            //判断 是否在同一行，没有必要，n每次都在递增
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){//同一列，同一斜线
                return false;
            }
        }
        return true;
    }
    //写一方法，可以将皇后摆放的位置输出
    private void print(){
        count++;
        for (int i = 0; i <array.length ; i++) {
            System.out.print(array[i]+"");
        }
        System.out.println();
    }
}
