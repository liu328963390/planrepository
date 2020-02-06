package com.plan.newyear.util.linked;

/**
 * 双向链表
 */
public class DoubleLinkedList {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试....");
        //创建节点
        HearoNode2 hear1 = new HearoNode2(1, "宋江","及时雨");
        HearoNode2 hear2 = new HearoNode2(2, "无用", "智多星");
        HearoNode2 hear3 = new HearoNode2(3, "林冲", "豹子头");
        HearoNode2 hear4 = new HearoNode2(4, "阮小二", "猛龙");
        //创建一个双向链表
        DoubleLinked linked = new DoubleLinked();
        linked.add(hear1);
        linked.add(hear4);
        linked.add(hear3);
        linked.add(hear2);
        linked.list();
        //修改
        System.out.println("修改后.......");
        HearoNode2 hear5 = new HearoNode2(1, "公孙胜", "入云龙");
        linked.updateHero(hear5);
        linked.list();
        //删除
        System.out.println("删除后.....");
        linked.delNode(3);
        linked.list();
        //有序添加
        System.out.println("有序添加....");
        HearoNode2 hear6 = new HearoNode2(5, "李规", "黑旋风");
        DoubleLinked doubleLinked = new DoubleLinked();
        doubleLinked.addByOrder(hear6);
        doubleLinked.addByOrder(hear1);
        doubleLinked.addByOrder(hear3);
        doubleLinked.addByOrder(hear4);
        doubleLinked.addByOrder(hear2);
        doubleLinked.list();
    }
}

//创建一个双向链表的类
class DoubleLinked{
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HearoNode2 head = new HearoNode2(0,"","");

    public HearoNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HearoNode2 temp = head.next;
        while (true){
            //判断是否到链表最后
            if (temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp的next后移,一定小心
            temp = temp.next;
        }
    }

    //添加到一个节点到双向链表的最后
    public void add(HearoNode2 hearoNode){
        //思路：当不考虑编号的顺序时
        //1.找到当前链表的最后节点
        //2.将最后这个节点next指向新的切点
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HearoNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后
            if (temp.next == null){
                break;
            }
            //如果没有找到最且，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = hearoNode;
        hearoNode.pre = temp;
    }

    //修改一个节点的内容，可以看到双向链表的节点内容修改与单向链表一样，只是节点类型改成HearoNode2
    //修改节点的信息，根据no编号来修改，即no编号不能改
    //1.根据HearoNode的no来修改即可
    public void updateHero(HearoNode2 hearoNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据No编号
        //定义一个辅助变量
        HearoNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if (temp == null){
                break;//已经遍历完链表
            }
            if (temp.no == hearoNode.no){
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = hearoNode.name;
            temp.nickname = hearoNode.nickname;
        }else {//没有找到
            System.out.printf("没有找到编号 %d的节点\n",hearoNode.no);
        }
    }

    //从双向链表中删除一个节点
    //说明：对于双向链表，可以直接找到要删除的这个节点；找到后自我删除即可
    public void delNode(int no){
        //判断当前链表是否为空
        if (head.next == null){//空链表
            System.out.println("链表为空，无法删除");
            return;
        }
        HearoNode2 temp = head.next;//辅助变量（指针）
        boolean flag = false;//标志是否找到待删除节点的
        while (true){
            if (temp == null){//已经找到链表的最后节点的next
                break;
            }
            if (temp.no == no){
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag){
            //可以删除
//            temp.next = temp.next.next;
            temp.pre.next = temp.next;
            //如果是最后个节点，就不需要执行下面这句，否则出现空指针
            if (temp.next !=null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的%d节点不存在\n",no);
        }
    }

    public void addByOrder(HearoNode2 hearoNode){
        //因为头节点不能动，因此仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HearoNode2 temp = head;
        boolean flag = false;//flag标识添加的编号是否存在，默认为false
        while (true){
            if (temp.next == null){//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > hearoNode.no){//位置找到，就在temp的后面插入
                break;
            }else if (temp.next.no == hearoNode.no){//说明希望添加的heroNode的编号已经存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag){//不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n",hearoNode.no);
        }else {
            //插入到链表中，temp的后面
            hearoNode.next = temp.next;
            temp.next = hearoNode;
        }
    }
}

//定义一个hearoNode,每个hearoNode对象就是一个节点
class HearoNode2{
    public int no;
    public String name;
    public String nickname;
    public HearoNode2 next;//指向下一个节点,默认为null
    public HearoNode2 pre;//指向前一个节点，默认为null
    //构造器
    public HearoNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，重写toString
    @Override
    public String toString() {
        return "HearoNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}