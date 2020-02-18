package com.plan.newyear.util.time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);

    }

    //前序遍历
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("空树");
        }
    }

    //创建赫夫曼树的方法

    /**
     *
     * @param arr 需要创建成赫夫曼树的树组
     * @return 创建好后的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr){
        //为了操作方便：遍历arr数组；将arr的每个元素构建成一个Node;将Node放入到ArrayList中
        List<Node> list=new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }

        while (list.size()>1) {
            //排序从小到大
            Collections.sort(list);
            System.out.println(list);
            //取出根节点权值最小的两颗二叉树
            //取出权值最小的节点（二叉树）
            Node left = list.get(0);
            //取出第二小的节点
            Node right = list.get(1);
            //构建一颗新二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            //从list中删除处理过的二叉树
            list.remove(left);
            list.remove(right);
            //将parent加到list中
            list.add(parent);
            Collections.sort(list);
            System.out.println("第一次处理后" + list);
        }
        //返回赫夫曼树的root节点
        return list.get(0);
    }
}

//创建节点类
//为了让Node对象持续排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node>{
    int value;//节点的权值
    Node left;//指向左子节点
    Node right;//指向右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}
