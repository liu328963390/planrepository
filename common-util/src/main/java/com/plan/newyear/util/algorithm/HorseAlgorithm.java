package com.plan.newyear.util.algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 骑士周游世界（马踏棋盘算法）：
 */
public class HorseAlgorithm{
    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean[] visited;
    //使用一个属性标记棋盘的所有位置都被访问过
    private static boolean finished;//如果为true表示成功

    /**
     * 根据当前位置（Point对象），计算出马还能走哪些位置（Point），并放入到一个集合中（ArrayList),最多有8个位置
     * @param currentPoint 当前点
     * @return
     */
    public static ArrayList<Point> next(Point currentPoint){
        //创建一个ArrayList集合
        ArrayList<Point> points = new ArrayList<>();
        //创建一个point
        Point p = new Point();
        //表示马可以走这个位置
        if ((p.x = currentPoint.x-2)>= 0 && (p.y = currentPoint.y-1)>=0){
            points.add(new Point(p));
        }
        if ((p.x = currentPoint.x-1)>=0 && (p.y = currentPoint.y-2)>=0){
            points.add(new Point(p));
        }
        if ((p.x = currentPoint.x+1)<X && (p.y=currentPoint.y-2)>=0){
            points.add(new Point(p));
        }
        if ((p.x = currentPoint.x+2)<X && (p.y=currentPoint.y-1)>=0){
            points.add(new Point(p));
        }
        if ((p.x=currentPoint.x+2)<X && (p.y = currentPoint.y+1)<Y){
            points.add(new Point(p));
        }
        if ((p.x=currentPoint.x+1)<X && (p.y = currentPoint.y+2)<Y){
            points.add(new Point(p));
        }
        if ((p.x=currentPoint.x-1)>=0 && (p.y = currentPoint.y+2)<Y){
            points.add(new Point(p));
        }
        if ((p.x=currentPoint.x-2)>=0 && (p.y = currentPoint.y+1)<Y){
            points.add(new Point(p));
        }
        return points;
    }

    /**
     * 完成马踏棋盘的算法
     * @param chees 棋盘
     * @param row 马的当前位置的行 从0开始
     * @param column 马的当前位置的列 从0开始
     * @param step 马走的第几步，初始位置就是第1步
     */
    public static void horseTravel(int[][] chees,int row,int column,int step){
        chees[row][column] = step;
        visited[row*X+column] = true;//标记该位置为已经访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> next = next(new Point(column, row));
        //对next进行排序，排序的规则就是对next的所有的Point对象的下一步的位置的数目，进行非递减排序
        sorts(next);//此步就是用了贪心算法的优化
        //遍历next
        while (!next.isEmpty()){
            Point p = next.remove(0);//取出下一个可以走的位置
            //判断该点是否已经访问过
            if (!visited[p.y*X+p.x]){//说明还没有访问过
                horseTravel(chees,p.y,p.x,step+1);
            }
        }
        //判断是否完成任务，若没有完成，则全部置0,使用step和应该走的步数比较
        //说明：step<X*Y 成立的情况有两种
        //1。棋盘到目前的位置，仍然没有走完
        //2。棋盘处于一个回溯过程
        if (step<X*Y && !finished){
            chees[row][column] = 0;
            visited[row*X+column] = false;
        }else {
            finished = true;
        }
    }


    /**
     * 根据当前这一步的所有的下一步的选择位置，进行非递减排序,减少回溯的次数
     * @param points
     */
    public static void sorts(ArrayList<Point> points){
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1点的下一步的所的位置的个数
                int size = next(o1).size();
                int size1 = next(o2).size();
                if (size<size1){
                    return -1;
                }else if (size == size1){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }

    /**
     * 马踏棋盘算法综合
     * @param x 行
     * @param y 列
     * @param row 马所在的行
     * @param column 马所在的列
     * @param step 所走的步数
     */
    public static void wentChess(int x,int y,int row,int column,int step){
        //创建棋盘
        X = x;Y=y;
        int[][] chess = new int[X][Y];
        visited = new boolean[X*Y];
        horseTravel(chess,row-1,column-1,step);
        //输出棋盘
        for (int[] ints : chess) {
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //测试算法是否正确
//        X = 6;
//        Y = 6;
//        int row =3;//马初始位置的行，从1开始编号
//        int column = 3;//马初始位置的列，从1开始编号
//        //创建棋盘
//        int[][] chees = new int[X][Y];
//        visited = new boolean[X*Y];//初始值都是false
        long start = System.currentTimeMillis();
//        horseTravel(chees,row-1,column-1,1);
        wentChess(6,6,6,1,1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时="+(end-start));
        //输出棋盘的最后情况
//        for (int[] chee : chees) {
//            for (int i : chee) {
//                System.out.print(i+"  \t ");
//            }
//            System.out.println();
//        }
    }
}
