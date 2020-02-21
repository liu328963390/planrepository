package com.plan.newyear.util.algorithm;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法：
 */
public class DijkstraAlgorithm{
    public static final int N = 65535;
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};
        //创建图对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.disj(2);
        graph.showDisj();
    }
}

/**
 * 已访问顶点集合
 */
class VisitedVertex{
    public int[] already_arr;//记录各个顶点是否访问过，1表示访问过，0未访问，会动态更新
    public int[] pre_visited;//每个下标对应的值为前一个顶点下标，会动态更新
    public int[] dis;//记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会动态更新，求的最短距离就会放到dis集合中

    /**
     * 构造器
     * @param length 顶点的个数
     * @param index 出发顶点对应的下标，比如G点，下标就是6
     */
    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis,65535);//把dis的数组全部填充
        this.already_arr[index] = 1;//设置出发顶点被访问过
        this.dis[index] = 0;//设置出发顶点的访问距离为0
    }

    /**
     * 判断index顶点是否被访问过
     * @param index 顶点的下标
     * @return 如果访问过，就返回true,否则返回false
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index这个顶点的距离
     * @param index 更新的具体是谁
     * @param len 距离是多少
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前驱顶点为index的节点
     * @param index
     * @param pre
     */
    public void updatePre(int index,int pre){
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点，比如G完后，就是A点作为新的访问顶点（注意不是出发顶点）
     * @return
     */
    public int updateArray(){
        int min = 65535,index =0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] ==0 && dis[i]<min){
                min = dis[i];
                index = i;
            }
        }
        //更新index,顶点被访问过
        already_arr[index] =1;
        return index;
    }


    /**
     * 显示结果,将三个数组的情况输出
     */
    public void show(){
        System.out.println("======================");
        //输出 already_arr
        for (int i : already_arr) {
            System.out.print(i+"");
        }
        //输出pre_visited
        System.out.println();
        for (int i : pre_visited) {
            System.out.print(i+"");
        }
        //输出dis
        System.out.println();
        for (int di : dis) {
            System.out.print(di+"");
        }
        System.out.println();
        //最后的最短距离，进行处理
        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for (int di : dis) {
            if (di != 65535){
                System.out.print(vertex[count]+"("+di+")");
            }else {
                System.out.print("N");
            }
            count++;
        }
        System.out.println();
    }
}

//创建图
class Graph{
    private char[] vertex;//存放顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex visitedVertex;//表示已经访问的顶点的集合

    //构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph(){
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }



    /**
     * 迪杰斯特拉算法实现
     * @param index 表示出发顶点对应的下标
     */
    public void disj(int index){
        visitedVertex = new VisitedVertex(vertex.length, index);
        update(index);//更新index下标顶点到周围顶点的距离和前驱顶点
        for (int i = 1; i < vertex.length; i++) {
            index = visitedVertex.updateArray();//选择并返回新的访问顶点
            update(index);//更新index下标顶点到周围顶点的距离和前驱顶点
        }
    }


    //显示出结果
    public void showDisj(){
        visitedVertex.show();
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
     * @param index 表示出发顶点对应的下标
     */
    private void update(int index){
        int len = 0;
        //根据遍历邻接矩阵的matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //len含义是：出发顶点到index顶点的距离+从index顶点到i顶点的距离的和
            len = visitedVertex.getDis(index)+matrix[index][i];
            //如果i顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!visitedVertex.in(i) && len<visitedVertex.getDis(i)){
                visitedVertex.updatePre(index,i);//更新i顶点的前驱为index
                visitedVertex.updateDis(i,len);//更新出发顶点到i顶点的距离
            }
        }
    }

}
