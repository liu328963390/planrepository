package com.plan.newyear.util.recursion;

/**
 * 递归：解决迷宫问题
 *
 */
public class Labyrinth {
    public static void main(String[] args) {
        //通过打印问题，回顾递归调用机制
//        test(4);
//        System.out.println(factorial(3));
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i=0;i<7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i <8 ; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板，1表示
        map[3][1] =1;
        map[3][2] =1;
        map[3][3] =1;
        map[3][4] =1;
        map[1][4] =1;
        map[2][4] =1;
        map[3][4] =1;

        //输出地图
        System.out.println("地图的情况...");
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+"");
            }
            System.out.println();
        }

        //使用递归回溯，找路
        setWay(map,1,1);
        //输出新的地图,小球走过，并丁光训过的递归
        System.out.println("新地图的情况...");
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+"");
            }
            System.out.println();
        }

    }


    //使用递归回溯来给小球给小球找路
    /**
     * 约定：
     * 1。map表示地圈
     * 2。i,j表示从地图的哪个位置开始出发（1，1）
     * 3。如果小球能捯map[6][5]位置，则说明通路找到
     * 4。约定：当map[i][j]为0表示该点没有走过，当为1表示墙，2表示通路可以走，3表示该点已经走过，但是走不能
     * 5。在走迷宫时，需要确定一个策略（方法）：先走下面->右->上->左，如果该点走不通再回溯
     */

    /**
     *
     * @param map 表示地圈
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true,否则返回false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5] == 2){//通路已经找到
            return true;
        }else {
            if (map[i][j] == 0){//如果该点走没有走过
                //按策略（方法）：先走下面->右->上->左，如果该点走不通再回溯
                map[i][j] = 2;//假定该点是可以走通
                if (setWay(map,i+1,j)){//向下走
                    return true;
                }else if (setWay(map,i,j+1)){//向右走
                    return true;
                }else if (setWay(map,i-1,j)){//向上走
                    return true;
                }else if (setWay(map,i,j-1)){//向左走
                    return true;
                }else {
                    //说明该点是走不通的，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {//如果map[i][j] !=0,map[i][j]可能是1，2，3
                return false;

            }
        }
    }

    public static void test(int n){
        if (n>2){
            test(n-1);
        }
        System.out.println("n="+n);
    }

    //阶乘
    public static int factorial(int n){
        if (n ==1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }
}
