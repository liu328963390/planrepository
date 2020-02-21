package com.plan.newyear.util.algorithm;

/**
 * 汉诺塔：分治算法
 */
public class DivideConTower{
    private static int count;
    public static void main(String[] args) {
        int i = hanTower(26, 'A', 'B', 'C');
        System.out.println(i);
    }

    /**
     * 汉诺塔的移动方法：使用分治算法
     * @param num 多少个盘
     * @param a 第一根柱子
     * @param b 第二根柱子
     * @param c 第三根柱子
     */
    public static int hanTower(int num,char a,char b,char c){
        //如果只有一个盘
        if (num == 1){
//            System.out.println("第1个盘从"+a+"->"+c);
            count +=1;
        }else {
            //如果我们有n>=2情况，我们总是可以看做两个盘（最下边的一个盘，上面的所有盘）
            //1.先把最上面的所有盘A->B，移动过程会使用到c
            hanTower(num-1,a,c,b);
            //把最下边的盘A->C
//            System.out.println("第"+num+"个盘从"+a+"->"+c);
            count +=1;
            //把B塔的所有盘从B->C,移动过程使用到a塔
            hanTower(num-1,b,a,c);
        }
        return count;
    }
}
