package com.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 和其他BlockingQueue不一样，SynchronousQueue不存储元素
 * 同步队列put了一个元素，必须从里面先take取出来，否则不能再put进去值
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();//同步队列

        //put值进去
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+" put 1");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+" put 1");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"T1").start();

        //take值出来
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"=>"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"=>"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"=>"+blockingQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"T2").start();

    }
}
