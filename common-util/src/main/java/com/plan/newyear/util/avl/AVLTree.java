package com.plan.newyear.util.avl;

public class AVLTree {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr= {10,12,8,9,7,6};
//        int[] arr = {10,11,7,6,8,9};
        int[] arr = {2,1,6,5,7,3};
        //创建一个对象
        ANodeTree tree = new ANodeTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        //遍历
        tree.midOrder();
        //没有做平衡处理之前，情况是
        System.out.println(tree.getRoot().value);
        System.out.println(tree.getRoot().height());
        System.out.println(tree.getRoot().leftHeight());
        System.out.println(tree.getRoot().rightHeight());
        System.out.println(tree.getRoot().left);
        System.out.println(tree.getRoot().right);
    }
}
