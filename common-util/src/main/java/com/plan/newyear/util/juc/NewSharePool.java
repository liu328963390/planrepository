package com.plan.newyear.util.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NewSharePool {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(8, 50, 20L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 80; i++) {
            pool.execute(()->{
                System.out.println(Thread.currentThread().getName() +"\t checking");
            });
        }

    }
}
