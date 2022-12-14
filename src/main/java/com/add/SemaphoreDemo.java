package com.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//信号量
//作用：多个共享资源互斥的使用，并发限流，控制最大的线程数
public class SemaphoreDemo {
    public static void main(String[] args) {
        //默认线程数量:停车位        限流
        //总共6个车
        Semaphore semaphore=new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                //acquire() 得到,假设如果已经满了，等待，等待被释放为止
                try {
                    semaphore.acquire();//获得停车位
                    System.out.println(Thread.currentThread().getName()+"占用停车位！");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开停车位！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    //release() 释放，会将当前的信号量释放+1，然后唤醒等待的线程
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
