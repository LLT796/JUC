package com.pool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Executors工具类：三大方法
//使用线程池之后要使用线程池来创建线程
public class Demo01 {
    public static void main(String[] args) {//追踪源码
        //ExecutorService threadpool=Executors.newSingleThreadExecutor();//单个线程
        //ExecutorService threadpool=Executors.newFixedThreadPool(5);//固定线程数
        ExecutorService threadpool=Executors.newCachedThreadPool();//可伸缩的线程数

        try {
            for (int i = 0; i < 10; i++) {//10个任务
                //使用线程池之后，要使用线程池来创建线程
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //线程池用完，程序结束，关闭线程池
            threadpool.shutdown();
        }
    }
}
