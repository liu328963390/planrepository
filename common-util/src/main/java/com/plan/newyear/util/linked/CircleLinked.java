package com.plan.newyear.util.linked;

/**
 * 环形链表：约瑟夫问题
 */
public class CircleLinked {
    public static void main(String[] args) {
        //测试构建环形链表
        CirleSingleLinked circleLinked = new CirleSingleLinked();
        circleLinked.addBoy(5);
        circleLinked.showBoy();
        //测试小孩出圈
        circleLinked.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CirleSingleLinked{
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);
    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums){
        //对nums作一个数据校验
        if (nums<1){//至少要有一个
            System.out.println("nums的值不正确");
            return;
        }
        Boy current = null;//辅助变量（指针）帮助构建环形链表
        //使用一个for循环创建一个环形链表
        for (int i = 1; i <=nums ; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i==1){
                first = boy;
                first.setNext(first);//构成一个环
                current = first;//让current指向第一个小孩
            }else {
                current.setNext(boy);//
                boy.setNext(first);
                current=boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        //判断链表是否为空
        if (first == null){
            System.out.println("链表为空，没有数据");
            return;
        }
        //因为first是不能动，因些我们仍然使用一个辅助指针完成遍历
        Boy current = first;
        while (true){
            System.out.printf("小孩的编号%d\n",current.getNo());
            if (current.getNext() == first){//说明已经遍历完毕
                break;
            }
            current = current.getNext();//current后移

        }
    }

    //根据用户的输入，计算小孩出圈的顺序

    /**
     *
     * @param startno 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startno,int countNum,int nums){
        //先对数据进行校验
        if (first==null || startno<1 || startno>nums){
            System.out.println("参数输入有误，请重新输入。。。");
            return;
        }

        //创建一个辅助指针，帮助小孩出圈
        Boy helper = first;
        //需要创建一个辅助指针helper,事先应该指向环形链表的最后这个节点
        while (true){
            if (helper.getNext()==first){ //说明helper指向最后小孩节点
                break;
            }
            helper=helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int i = 0; i < startno-1; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩报数前，让first和helper指针同时的移动m-1次,然后出圈
        //这里是一个循环的操作，直到圈中只有一个节点
        while (true){
            if (helper == first){//说明圈中只有一个节点
                break;
            }
            //让first和helper同时移动countnum-1次
            for (int i = 0; i <countNum-1 ; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first=first.getNext();
            helper.setNext(first);//构建
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());
    }
}

//定义一个节点
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
