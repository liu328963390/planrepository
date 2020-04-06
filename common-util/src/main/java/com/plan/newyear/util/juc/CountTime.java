package com.plan.newyear.util.juc;

import java.util.concurrent.CountDownLatch;

public class CountTime {

    public static void main(String[] args) throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(8);
        for (int i = 0; i < 8; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t号leave home");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t *** open the class door,main thread is master");
    }
}
