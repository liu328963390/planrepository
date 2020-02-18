package com.plan.newyear.util.avl;

public class Node{
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

    //返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

    //返回以该节点为根节点的树的高度
    public int height(){
        return Math.max(left == null?0:left.height(),right==null?0:right.height())+1;
    }

    //左旋转
    private void leftRotate(){
        //创建新节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点左子树
        newNode.left = left;
        //把新的节点的右子树设置成带你过去节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        //把当前节点的左子树（左子节点）设置成新的节点
        left = newNode;
    }

    //右旋转
    private void rightRotate(){
        Node newnode = new Node(value);
        newnode.right = right;
        newnode.left = left.right;
        value = left.value;
        left = left.left;
        right = newnode;
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
        //当添加完一个节点后，如果：（右子树的高度-左子树高度）>1,左旋转
        if (rightHeight()-leftHeight()>1){
            //如果它的右子树的左子树的高度大于它的右子树的高度，先对它的右子树（右子节点）进行右旋转，然后再对当前节点进行左旋转
            if (right != null && right.rightHeight()<right.leftHeight()) {
                right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
            }
            return;
        }
        //当添加完一个节点后，如果：（左子树的高度-右子树高度）> 1,右旋转
        if (leftHeight()-rightHeight()>1){
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (left!=null && left.rightHeight()>left.leftHeight()){
                //先对当前节点的左节点（左子树）->左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            }else {
                rightRotate();
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
