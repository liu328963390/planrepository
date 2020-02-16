package com.plan.newyear.util.tree;

import java.util.Arrays;

/**
 * 二叉树的遍历
 */
public class BinaryTrees {
    public static void main(String[] args) {
        //创建一颗二叉树
        Binarys binarys = new Binarys();
        //创建需要的节点
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢");
        HeroNode node4 = new HeroNode(4, "公孙");
        HeroNode node5 = new HeroNode(5, "李逵");
        HeroNode node6 = new HeroNode(6, "柴进");
        HeroNode node7 = new HeroNode(7, "林冲");
        HeroNode node8 = new HeroNode(8, "阮小二");
        //说明，先手动创建该二叉树，后面学习递归的方式创建二叉树
        node1.setLeft(node2);
        node2.setLeft(node4);
        node1.setRight(node3);
        node2.setRight(node6);
        node3.setLeft(node5);
        node3.setRight(node7);
        node4.setLeft(node8);
        binarys.setRoot(node1);
        //测试
        System.out.println("前序遍历");
        binarys.preOrder();
        HeroNode node = binarys.preSearch(1);
        System.out.println("中序遍历");
        binarys.midOrder();
        binarys.midSearch(1);
        System.out.println("后序遍历");
        binarys.postOrder();
        binarys.postSearch(1);

        //查找


    }
}

//定义一个Binary二叉树
class Binarys{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void midOrder(){
        if (this.root != null){
            this.root.midOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历
    public HeroNode preSearch(int no){
        if (this.root != null){
            return root.preSearch(no);
        }else {
            return null;
        }
    }

    public HeroNode midSearch(int no){
        if (this.root != null){
            return root.midSearch(no);
        }else {
            return null;
        }
    }

    public HeroNode postSearch(int no){
        if (this.root != null){
            return root.postSearch(no);
        }else {
            return null;
        }
    }
}
//先创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void midOrder(){
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.midOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树
        if (this.right !=null){
            this.right.midOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找

    /**
     *
     * @param no 查找no
     * @return 如果找到就返回该node,如果没有找到返回null
     */
    public HeroNode preSearch(int no){

        if (this == null){
            return null;
        }
        System.out.println("前序遍历1");
        //比较当前节点是不是
        if (this.no == no){
            return this;
        }
        //判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //如果左递归前序查找，找到结点 ，则返回
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.preSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        //左递归前序查找，找到节点，则返回，否则继续判断
        //当前的节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
        if (this.right != null){
            resNode = this.right.preSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode midSearch(int no){

        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.midSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        System.out.println("前序遍历2");
        //如果找到，则返回，如果没有找到，就和当前节点比较，如果是则返回当前节点
        if (this.no == no){
            return this;
        }
        //否则继续进行右递归的中序查找
        if (this.right != null){
            resNode = this.right.midSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postSearch(int no){

        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.postSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        System.out.println("前序遍历3");
        if (this.no == no){
            return this;
        }
        return resNode;
    }
}

