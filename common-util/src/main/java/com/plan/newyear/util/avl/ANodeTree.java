package com.plan.newyear.util.avl;


public class ANodeTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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
