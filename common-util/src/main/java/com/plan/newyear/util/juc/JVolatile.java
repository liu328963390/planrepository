package com.plan.newyear.util.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *一、 volatile 关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见，实时刷新主存中的数据
 * 相较于synchronized是一种较轻量级的同步策略
 * 注意：
 * 1。volatile 不具备“互斥性”
 * 2。volatile 不能保证变量的“原子性”
 *
 *二、 i++的原子性问题：i++的操作实际上分为三个步骤“读-改-写”
 *     int i=10;
 *     i=i++;//10
 *
 *     int temp = i;
 *     i=i+1;
 *     i =temp;
 * 三、原子变量：java.util.concurrent.atomic包下提供了常用的原子变量
 *    1.有volatile的特性，里面的变量都有volatile修饰，保证内存可见性
 *    2.用了CAS（Compare-And-Swap)算法保证数据的原子性：
 *       CAS算法是硬件对于并发操作共享数据的支持
 *       CAS包含三个操作数：
 *       内存值V
 *       预估值A
 *       更新值B
 *       当且仅当V==A时，V==B，否则将不作任何操作
 */
public class JVolatile{
    public static void main(String[] args) {
        Threads threads = new Threads();
        for (int i = 0; i < 10; i++) {
            new Thread(threads).start();
        }

       /* 一、while (true){
//            synchronized (threads) { //加锁对内存损耗
                if (threads.isFlag()) {
                    System.out.println("===================");
                    break;
                }
//            }
        }*/
    }
}

class Threads implements Runnable{
    private volatile boolean flag = false;
//    private int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger();
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*一、
        flag = true;
        System.out.println("flag="+isFlag());*/
        System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());

    }
}