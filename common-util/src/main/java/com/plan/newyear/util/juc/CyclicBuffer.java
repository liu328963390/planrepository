package com.plan.newyear.util.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBuffer {

    private static final int NUMBER = 8;

    public static void main(String[] args) {
        CyclicBarrier findPeople = new CyclicBarrier(NUMBER, () -> {
            System.out.println("find eight people");
        });

        for (int i = 1; i <= 8; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"\t collection people");
                    findPeople.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
