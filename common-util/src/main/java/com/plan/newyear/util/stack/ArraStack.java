package com.plan.newyear.util.stack;

import java.util.Scanner;

/**
 * 栈
 */
public class ArraStack {
    public static void main(String[] args) {
        //测试
        //先创建一个ArrayStack对象，表示 栈
        ArrayStack stack = new ArrayStack(5);
        String key="";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示显示栈");
            System.out.println("ppush:表示显示栈");
            System.out.println("ppop:表示显示栈");
            key = scanner.next();
            switch (key){
                case "s":
                    stack.llist();
                    break;
                case "p":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.ppush(value);
                    break;
                case "o":
                    try {
                        int response = stack.ppop();
                        System.out.printf("出栈的数据是%d\n",response);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出...");
    }
}

//定义一个ArrayStack表示栈
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就放在该数组
    private int top = -1;//top表示栈顶，初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈-push
    public void ppush(int value){
        //先判断栈是否满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] =value;
    }

    //出栈 -ppop,将栈所有数据返回
    public int ppop(){
        //先判断栈是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据。。。");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈（显示栈的情况）;遍历时需要从栈顶开始写数据
    public void llist(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}