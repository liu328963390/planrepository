package com.plan.newyear.util.algorithm;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法：
 */
public class KruskalAlgorithm{

    private int edgeNum;//边的个数
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE;//使用INF变量表示两个顶点不能连通

    /**
     * 构造器
     * @param vertex
     * @param matrix
     */
    public KruskalAlgorithm(char[] vertex, int[][] matrix) {
//        this.vertex = vertex;
//        this.matrix = matrix;
        //初始化顶点数和边的个数
        int vlen = vertex.length;
        //初始化顶点 ,用的是复制拷贝的方式
        this.vertex = new char[vlen];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i]=vertex[i];
        }
        //初始化边,用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if (this.matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }

    //

    /**
     * 按照边的权值进行排序的处理，用冒泡
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight>edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] =edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     * 顶点对应的下标
     * @param ch 顶点的值，比如'a','b'等
     * @return 返回ch顶点对应的下标，如果找不到，则返回-1
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch){
                return i;
            }
        }
        //找不到，返回-1
        return -1;
    }

    /**
     * 获取图中的边，放到EData[]数组中，后面需要遍历该数组
     * 通过matrix邻接矩阵来获取
     * EData[] 形式如[['a','b',12],['a','c',23]]
     * @return EData数组
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i+1; j < vertex.length; j++) {
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(vertex[i],vertex[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends 数组记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    public void kruskal(){
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] result = new EData[edgeNum];

        //获取原始图中所有的边的集合，初始一共有12条边
        EData[] edges = getEdges();
        //按照边的权值大小进行排序（从小到大）
        sortEdges(edges);

        //遍历edges数组，将边添加到最小生成树中时，判断准备加入的边是否形成了回路，如果没有，就加result,否则就不加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);
            //获取到第i条边的第二个顶点（终点），当条边的终点
            int p2 = getPosition(edges[i].end);
            //获取p1这个顶点在已有的最小生成树中的终点
            int m = getEnd(ends,p1);
            //获取p2这个顶点在已有的最小生成树中的终点
            int n = getEnd(ends,p2);
            //判断是否构成回路
            if (m !=n){//没有构成回路
                ends[m]=n;//设置m在“已有最小生成树”的终点<e,f> [0,0,0,0,5,0,0,0,0,0,0,0]
                result[index++] = edges[i];//有一条边加入到result数组
            }
        }
        //统计并打印“最小生成树”，输出result
        for (int i = 0; i < index; i++) {
            System.out.println("最小生成树为="+result[i]);
        }

    }

    public void print(){
        System.out.println("邻接矩阵为.....");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%10d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[] vertex = {'a','b','c','d','e','f','g'};
        int[][] matrix={
                /*a*//*b*//*c*//*d*//*e*//*f*//*g/
                /*a*/{0,12,INF,INF,INF,16,14},
                /*b*/{12,0,10,INF,INF,7,INF},
                /*c*/{INF,10,0,3,5,6,INF},
                /*d*/{INF,INF,3,0,4,INF,INF},
                /*e*/{INF,INF,5,4,0,2,8},
                /*f*/{16,7,6,INF,2,0,9},
                /*g*/{14,INF,INF,INF,8,9,0}
        };
        //创建Kruskal对象实例
        KruskalAlgorithm algorithm = new KruskalAlgorithm(vertex, matrix);
        algorithm.print();
        EData[] edges = algorithm.getEdges();
        System.out.println("排序前"+Arrays.toString(edges));
        algorithm.sortEdges(edges);
        System.out.println(Arrays.toString(edges));
        algorithm.kruskal();
    }

}

//创建一个边类EData,它的对象实例就表示一条边
class EData{
    char start;//边的起点
    char end;//边的终点，边的另外一个点，比如b->e
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //便于显示输出边的信息
    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                ">=" + weight +
                '}';
    }
}