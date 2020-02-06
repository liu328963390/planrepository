package com.plan.newyear.util.linked;


import java.util.Stack;

/**
 * 单向链表：添加，显示单向链表
 * 水浒108英雄排名
 */
public class SingleLinked {


    public static void main(String[] args) {
        //测试
        //创建节点
        HearoNode hear1 = new HearoNode(1, "宋江","及时雨");
        HearoNode hear2 = new HearoNode(2, "无用", "智多星");
        HearoNode hear3 = new HearoNode(3, "林冲", "豹子头");
        HearoNode hear4 = new HearoNode(4, "阮小二", "猛龙");
        //创建要给链表
        System.out.println("不按序号排列输出 --------------------");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.add(hear1);
        singleLinkedList.add(hear4);
        singleLinkedList.add(hear2);
        singleLinkedList.add(hear3);
        //显示
        singleLinkedList.list();
        //测试逆序打印单链表
        System.out.println("测试逆序打印单链表.....");
        reversePrint(singleLinkedList.getHead());
        System.out.println("按序号排列输出----------------------------");
        SingleLinkedList linkedList = new SingleLinkedList();
        //加入
        linkedList.addByOrder(hear4);
        linkedList.addByOrder(hear2);
        linkedList.addByOrder(hear1);
        linkedList.addByOrder(hear3);
        linkedList.addByOrder(hear4);
        //显示
        System.out.println("原来链表的情况......");
        linkedList.list();
        //反转链表
        System.out.println("反转单链表.....");
        reversetList(linkedList.getHead());
        linkedList.list();

        HearoNode hear5 = new HearoNode(7, "小上", "ad ");
        linkedList.updateHero(hear5);
        //显示
        linkedList.list();
        System.out.println("有效的节点个数="+getLength(linkedList.getHead()));
        //倒数第2个节点
        HearoNode res = findLastIndex(linkedList.getHead(),2);
        System.out.println("res="+res);

        //删除一个节点
        singleLinkedList.delNode(1);
        singleLinkedList.delNode(4);
        singleLinkedList.delNode(2);
        singleLinkedList.delNode(3);
        System.out.println("删除后---------------");
        singleLinkedList.list();
    }

    //方法：获取到单链表的节点的个数 （如果是带头节点的链表，需求不统计头节点）

    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HearoNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量,没有统计头节点
        HearoNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个节点
    //思路：
    //1。编写一个方法接收head节点，同时接收一个index
    //2.index表示的是倒数第index个节点
    //3。先把链表从头到尾遍历，得到链表的总长度，getLength
    //4.得到size后，从链表的第一个开始遍历（size-index)个，就可以得到
    //若找到了，则返回；未找到则返回null
    public static HearoNode findLastIndex(HearoNode head,int index){
        //判断如果链表为空，返回null
        if (head.next == null){
            return null;
        }
        //第一个遍历 得到链表的长度（节点个数 ）
        int size = getLength(head);
        //第二次遍历size-index位置，就是我们的倒数的第k个节点
        //先做一个index的校验
        if (index<=0 || index>size){
            return null;
        }
        //定义一个辅助变量
        HearoNode current = head.next;
        //遍历，for循环定位倒数的index
        for (int i = 0;i<size-index;i++){
            current = current.next;
        }
        return current;
    }

    //将单链表进行反转
    public static void reversetList(HearoNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }

        //定义一个辅助的指针（变量）,帮助我们遍历原来的链表
        HearoNode current = head.next;
        HearoNode next = null;//指向当前节点[current]的下一个节点
        HearoNode reverseHead = new HearoNode(0,"","");
        //遍历 原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (current != null){
            next = current.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            current.next = reverseHead.next;//将current的下一个节点指向新的链表的最前端
            reverseHead.next = current;//将current连接到新的链表上
            current = next;//让current后移
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    //使用栈实现逆序打印单链表
    public static void reversePrint(HearoNode head){
        if (head.next == null){//空链表无法打印
            return;
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HearoNode> stack = new Stack<>();
        HearoNode current = head.next;
        //将链表的所有节点压入栈中
        while (current!= null){
            stack.push(current);
            current = current.next;//让current后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size()>0){
            System.out.println(stack.pop());//栈的特点是先进后出
        }
    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HearoNode head = new HearoNode(0,"","");

    public HearoNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HearoNode hearoNode){
        //思路：当不考虑编号的顺序时
        //1.找到当前链表的最后节点
        //2.将最后这个节点next指向新的切点
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HearoNode temp = head;
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
        //将最后这个节点的next指向新的节点
        temp.next = hearoNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HearoNode hearoNode){
        //因为头节点不能动，因此仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HearoNode temp = head;
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

    //修改节点的信息，根据no编号来修改，即no编号不能改
    //1.根据HearoNode的no来修改即可
    public void updateHero(HearoNode hearoNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据No编号
        //定义一个辅助变量
        HearoNode temp = head.next;
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

    //删除节点
    //思路：head节点不能动，需要一个temp辅助节点，找到待删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.no 和需要删除的节点的no作比较
    public void delNode(int no){
        HearoNode temp = head;
        boolean flag = false;//标志是否找到待删除节点的
        while (true){
            if (temp.next == null){//已经到链表的最后
                break;
            }
            if (temp.next.no == no){
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag){
            //可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的%d节点不存在\n",no);
        }
    }
    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HearoNode temp = head.next;
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
}

//定义一个hearoNode,每个hearoNode对象就是一个节点
class HearoNode{
    public int no;
    public String name;
    public String nickname;
    public HearoNode next;//指向下一个节点
    //构造器
    public HearoNode(int no, String name, String nickname) {
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