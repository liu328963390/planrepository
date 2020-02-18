package com.plan.newyear.util.arr;

/**
 * 二叉排序树
 */
public class TwoBinaryTree{
    public static void main(String[] args) {
        int[] arr = {-1,7,3,10,12,5,1,9,};
        BinarySort binarySort = new BinarySort();
        //循环的添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySort.add(new Node(arr[i]));
        }
        //中序遍历二叉排序树
//        binarySort.midOrder();

        //测试删除
        binarySort.delNode(7);
        binarySort.midOrder();
    }
}

//创建二叉排序树
class BinarySort{
    private Node root;
    //添加节点的方法
    public void add(Node node){
        if (root == null){
            root = node;//如果root为空，则直接让root指向node节点
        }else {
            root.add(node);
        }
    }
    //中序遍历
    public void midOrder(){
        if (root != null){
            root.midOrder();
        }else {
            System.out.println("二叉排序树为空");
        }
    }

    //查找要删除的节点
    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    //删除节点
    public void delNode(int value){
        if (root == null){
            return;
        }else {
            //1.需求先找到要删除的节点targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null){
                return;
            }
            //如果当前这颗二叉排序树只有一个节点
            if (root.left == null && root.right == null){
                root = null;
                return;
            }
            //查找targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null){
                //判断targetNode是父节点的左子节点，还是右子节点
                if (parent.left != null && parent.left.value == value){//左子节点
                    parent.left = null;
                }else if (parent.right != null && parent.right.value == value){//右子节点
                    parent.right = null;
                }
            }else if (targetNode.left != null && targetNode.right != null){//删除有两颗子树
//                int minVal = delRightTree(targetNode.right);
//                targetNode.value =minVal;
                targetNode.value = delLeftTree(targetNode.left);
            }else {//删除只有一颗子树
                //如果要删除的节点有左子节点
                if (targetNode.left != null){
                    if (parent != null) {
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {//targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }
                }else {//如果要删除的节点有右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 返回的是以node为根节点的二叉排序树的最小节点的值；删除node为根节点的二叉排序的最小节点
     * @param node 传入的节点，当作二叉排序树的根节点
     * @return 返回的是以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTree(Node node){
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while (target.left != null){
            target = target.left;
        }
        //target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    public int delLeftTree(Node node){
        Node target = node;
        while (target.right != null){
            target = target.right;
        }
        delNode(target.value);
        return target.value;
    }
}

//构建节点
class Node{
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    //添加节点的方法
    //递归的形式添加节点，注意需要满足二叉排序的要求
    public void add(Node node){
        if (node == null){
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的值关系
        if (node.value < this.value){
            //如果当前节点左子节点为null
            if (this.left == null){
                this.left = node;
            }else {
                //递归向左子树添加
                this.left.add(node);
            }
        }else if (node.value >= this.value){//添加的节点的值大于当前节点的值
            if (this.right == null){
                this.right = node;
            }else {
                //递归向右子树添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void midOrder(){
        if (this.left != null){
            this.left.midOrder();
        }
        System.out.println("中间的数是"+this.value);
        if (this.right != null){
            this.right.midOrder();
        }
    }


    /**
     *查找要删除的节点
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node search(int value){
        if (value == this.value){//找到就是该节点
            return this;
        }else if (value < this.value){//如果查找的值小于当前节点，向左子树递归查找
            //如果左子节点为空，就返回null
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        }else {//如果查找的值不小于当前节点，向右子树递归查找
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 要找的节点的值
     * @return 返回的是要删除的节点的父节点，如果没有数返回null
     */
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        }else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);//向左子树递归查找
            }else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);//向右子树递归查找
            }else {
                return null;//没有找到父节点
            }
        }
    }
}
