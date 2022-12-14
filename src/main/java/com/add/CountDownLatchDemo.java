package com.add;

import java.util.concurrent.CountDownLatch;

//计数器：-1
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6，必须要执行任务的时候再去使用。
        CountDownLatch countDownLatch=new CountDownLatch(6);

        //创建6个线程
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" go out!");
                countDownLatch.countDown();//数量-1
            },String.valueOf(i)).start();
        }

        countDownLatch.await();//等待计数器归零，然后再向下执行
        System.out.println("Closed door!");
    }
}
