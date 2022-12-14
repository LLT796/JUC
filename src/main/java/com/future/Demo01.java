package com.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用： Ajax
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ////发起一个请求 void
        //CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
        //    try {
        //        TimeUnit.SECONDS.sleep(2);
        //    } catch (InterruptedException e) {
        //        throw new RuntimeException(e);
        //    }
        //    System.out.println(Thread.currentThread().getName()+" runAsync=>Void");
        //});

        //System.out.println("1111");

        //completableFuture.get();//获取阻塞执行结果

        //有返回值的supplyAsync 异步回调
        CompletableFuture<Integer> completableFuture1=CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"supplyAsync=>Integer");
            int i=10/0;
            return 1024;
        });
        completableFuture1.whenComplete((t,u)->{
            System.out.println("t=>"+t);//正常的返回结果
            System.out.println("u=>"+u);//错误信息
        });

        /**
         *
         */
    }
}
