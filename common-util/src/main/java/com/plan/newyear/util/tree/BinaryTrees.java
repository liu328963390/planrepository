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
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
//        HeroNode node6 = new HeroNode(6, "柴进");
//        HeroNode node7 = new HeroNode(7, "林冲");
//        HeroNode node8 = new HeroNode(8, "阮小二");
        //说明，先手动创建该二叉树，后面学习递归的方式创建二叉树
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binarys.setRoot(node1);
        //测试
        System.out.println("删除前");
        binarys.preOrder();
//        HeroNode node = binarys.preSearch(1);
//        System.out.println("中序遍历");
//        binarys.midOrder();
//        binarys.midSearch(1);
//        System.out.println("后序遍历");
//        binarys.postOrder();
//        binarys.postSearch(1);
        binarys.deleteNode(5);
        System.out.println("删除后");
        binarys.preOrder();

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

    //删除节点
    public void deleteNode(int no){
        //首先考虑如果二叉树是空树root,如果只有一个root节点，则等价于将二叉树置空
        if (root != null){
            //如果只有一个root节点，这里立即判断root是不是就是要删除节点
            if (root.getNo() == no){
//                if (root.getLeft() != null && root.getRight() != null){
//                    root = root.getLeft();
//                    root.setRight(root.getRight());
//                }else if (root.getLeft()!=null){
//                    root = root.getLeft();
//                }else if (root.getRight() !=null){
//                    root = root.getRight();
//                }else {
                    root = null;
//                }
            }else {
                //进行递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("空树不能删除");
        }
    }
}
//先创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    //说明：
    //1.如果leftType==0表示指向的是左子树，如果1则表示 指向前驱节点
    private int leftType;
    //2.如果rightType == 0表示 指向是右子树，如果1表示指向后继节点
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    //递归删除节点
    //如果删除的节点是叶子节点，则删除该节点；如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no){
        /*
        1.因我二叉树是单向的，判断当前节点的子节点是否需要删除节点，而不能去判断当前这个节点是不是需要删除节点
        2.如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left = null；并且就返回（结束递归删除）
        3.如果当前节点的右子节点不为空，并且右子节点就是要删除节点，就将this.right = null,并且就返回（结束递归删除）
        4.如果第2，第3步没有删除节点，那么我们就需要向左子树进行递归删除
        5.如果第4步也没有删除节点，则应当向右子树进行递归删除。
         */
        if (this.left != null && this.left.no == no){
//            if (this.left.left != null && this.left.right != null){
//                this.left = this.left.left;
//                this.left.right = this.left.right;
//                return;
//            }else if (this.left.left != null){
//                this.left = this.left.left;
//                return;
//            }else if (this.left.right != null){
//                this.left = this.left.right;
//                return;
//            }else {
                this.left = null;
                return;
//            }
        }
        if (this.right != null && this.right.no == no){
//            if (this.right.left != null){
//                this.right = this.right.left;
//                return;
//            }else if (this.right.right != null){
//                this.right = this.right.right;
//                return;
//            }else if (this.right.left != null && this.right.left != null){
//                this.right = this.right.left;
//                return;
//            }else {
                this.right = null;
                return;
//            }
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
    }
}

