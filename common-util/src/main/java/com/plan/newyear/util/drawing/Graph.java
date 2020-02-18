package com.plan.newyear.util.drawing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图
 */
public class Graph{
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻结矩阵
    private int numofEdges;//表示边的数目
    //定义一个数组boolean[]，记录某个节点是否被访问过
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试
        int n = 8;//节点的个数
//        String vertexValu[]  = {"A","B","C","D","E","F","G","H"};
        String vertexValu[] = {"1","2","3","4","5","6","7","8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String value : vertexValu) {
            graph.insertVertex(value);
        }
        //添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(1,5,1);
        graph.insertEdge(4,6,1);
        graph.insertEdge(5,6,1);
        graph.showGraph();
        graph.dfs();
        System.out.println("广度优先");
        graph.broad();


    }

    //对所有的节点，都进行广度优先
    public void broad(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumofVertex(); i++) {
            if (!isVisited[i]){
                broad(isVisited,i);
            }
        }
    }

    /**
     * 对一个节点进行广度优化遍历
     * @param isVisited
     * @param i
     */
    private void broad(boolean[] isVisited,int i){
        int u;//表示队列的头节点对应的下标
        int w;//邻接节点的下标
        //需要一个队列，记录节点访问的顺序
        LinkedList linkedList = new LinkedList<>();
        //访问节点，输出信息
        System.out.print(getValueByIndex(i)+"->");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        linkedList.addLast(i);
        while (! linkedList.isEmpty()){
            //取出队列的头节点下标
             u = (Integer)linkedList.removeFirst();
             //得到第一个邻节点的下标w
             w = getFirstNeighbor(u);
             while (w != -1){
                 //判断是否访问过
                 if (!isVisited[w]){
                     System.out.print(getValueByIndex(w)+"->");
                     //标记已经访问
                     isVisited[w] = true;
                     //入队列
                     linkedList.addLast(w);
                 }
                 //以u为前驱点找w后面的下一个邻接节点
                 w = getNextNeighbor(u,w);//体现出广度优先
             }
        }

    }


    /**
     * 深度优先遍历算法
     * @param isVisited
     * @param i 第1次就是0
     */
    private void dfs(boolean[] isVisited,int i){
        //访问该节点
        System.out.print(getValueByIndex(i)+"->");
        //将该节点设置为已经访问
        isVisited[i] = true;
        int w = getFirstNeighbor(i);//查找节点i的第一个邻接节点
        while (w != -1){//说明有邻接节点
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i,w);
        }
    }

    //对dfs进行一个重载,遍历所有的节点，并进行dfs
    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        //遍历所有的节点进行dfs【回溯】
        for (int i = 0; i < getNumofVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //根据前一个邻结节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if (edges[v1][v2] >0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 得到一个邻接节点的下标w
     * @param index
     * @return 如果存在就返回对应的下标
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] >0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 构造器
     * @param n 表示顶点个数
     */
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numofEdges = 0;
//        isVisited = new boolean[n];
    }

    /**
     * 返回节点的个数
     * @return 返回节点的个数
     */
    public int getNumofVertex(){
        return vertexList.size();
    }

    /**
     * 得到边的数目
     * @return 返回边的数目
     */
    public int getNumofEdges(){
        return numofEdges;
    }

    //返回节点下标i（下标）对应的数据 0->"A" 1->"B"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }


    /**
     * 插入顶点或结点
     * @param vertex 待插入的顶点
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }



    /**
     * 添加边
     * @param v1 表示点的下标，即是第几个顶点 "A"-"B" "A"->"0" "B"->"1"
     * @param v2 表示第二个顶点的对应的下标
     * @param weight 表示用什么进行两个点的关联
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numofEdges++;
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
