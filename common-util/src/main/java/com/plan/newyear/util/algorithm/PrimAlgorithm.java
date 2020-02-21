package com.plan.newyear.util.algorithm;

import java.util.Arrays;

/**
 * 普利姆算法：
 */
public class PrimAlgorithm{
    public static void main(String[] args) {
        char[] data = new char[]{'a','b','c','d','e','f','g'};
        int vertex = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},
        };
        //创建MGraph对象
        MGraph mGraph = new MGraph(vertex);
        //创建一个最小生成树对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,vertex,data,weight);
        //输出
        minTree.showGraph(mGraph);
        minTree.prim(mGraph,3);
    }


}
//创建最小生成树->村庄的图
class MinTree{


    /**
     * 创建图的邻接矩阵
     * @param graph 图对象
     * @param verx 图对应的顶点的个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verx,char data[],int[][] weight){
        int i,j;
        for ( i = 0; i < verx; i++) {//顶点
            graph.data[i] = data[i];
            for (j = 0; j < verx; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }



    /**
     * 显示图的方法
     * @param graph 图对象
     */
    public void showGraph(MGraph graph){
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }


    /**
     * 编写prim算法，得到最小生成树
     * @param graph 图
     * @param verx 表示从图的第几个顶点开始生成 'a'->0
     */
    public void prim(MGraph graph,int verx){
        //visited[]表示标记（顶点）是否被访问过，默认元素的值都是0，表示没有访问过
        int[] visited = new int[graph.verx];
       /*  //visited初始化
       for (int i = 0; i < graph.verx; i++) {
            visited[i] =0;
        }*/
        //把当前这个节点标记为已访问
        visited[verx] = 1;
        //用h1,h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        //初始化minWeight,初始成一个大数，后面在遍历过程中，会被替换
        int minWeight = 100000;
        for (int i = 1; i < graph.verx; i++) {//因为有graph.verx个顶点 ，普利姆算法结束后，有graph.verx-1条边

            //确定每一次生成的子图，和哪个节点和这次遍历的节点的距离最近
            for (int j = 0; j < graph.verx; j++) {//j节点表示被访问过的节点
                for (int k = 0; k < graph.verx; k++) {//k节点表示还没有访问过的节点
                    if (visited[j] == 1 && visited[k] == 0 && graph.weight[j][k]<minWeight){
                        //替换minWeight（寻找已经访问过的节点和未访问的节点间的权值最小的边）
                        minWeight=graph.weight[j][k];
                        h1=j;
                        h2=k;
                    }
                }
            }
            //找到一条边是最小的
            System.out.println("edge<"+graph.data[h1]+","+graph.data[h2]+">weight:"+minWeight);
            //将当前这个节点标记为已经访问
            visited[h2] =1;
            //minWeight 重新设置为最大值 10000
            minWeight = 100000;
        }

    }
}

//创建一个图
class MGraph{
    int verx;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，就是我们的邻接矩阵

    /**
     * 构造器
     * @param verx 节点个数
     */
    public MGraph(int verx) {
        this.verx = verx;
        data = new char[verx];
        weight = new int[verx][verx];
    }
}