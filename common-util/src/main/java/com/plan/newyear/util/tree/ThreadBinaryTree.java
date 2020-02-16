package com.plan.newyear.util.tree;

/**
 * 线索化二叉树
 */
public class ThreadBinaryTree{
    public static void main(String[] args) {
        //测试中序线索二叉树
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node1 = new HeroNode(3, "宋江");
        HeroNode node2 = new HeroNode(6, "吴用");
        HeroNode node3 = new HeroNode(8, "卢俊义");
        HeroNode node4 = new HeroNode(10, "林冲");
        HeroNode node5 = new HeroNode(14, "关胜");
        //二叉树
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        ThreadBinarys binarys = new ThreadBinarys();
        binarys.setRoot(root);
        binarys.threadNodes();
        HeroNode left = node4.getLeft();
        HeroNode right = node4.getRight();
        System.out.println("left="+left);
        System.out.println("right="+right);
        //当线索化二叉树后，不能再使用原来的遍历方法
        binarys.threadList();
    }
}

//定义一个Binary二叉树
class ThreadBinarys{
    private HeroNode root;
    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载线索化方法
    public void threadNodes(){
        this.threadNodes(root);
    }

    //遍历中序线索化二叉树
    public void threadList(){
        //定义一个变量，临时存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null){
            //循环找到leftType==1的节点，第一个找到的就是8的节点，后面随着遍历变化而变化，因为当leftType==1时，说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1){
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node 当前需要线索化的节点
     */
    public void threadNodes(HeroNode node){
        //如果当前节点为空，无法线索化
        if (node == null){
            return;
        }

        //A.先线索化左子树
        threadNodes(node.getLeft());
        //B.线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null){
            //让前驱节点的右指针，指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针的类型
            pre.setRightType(1);
        }
        //每处理一个节点后， 让当前节点是下一个节点的前驱节点!!!!
        pre = node;
        //C.再线索化右子树
        threadNodes(node.getRight());
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