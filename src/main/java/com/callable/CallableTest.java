package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable与Runnable区别：
 * 1.可以有返回值
 * 2.可以抛出异常
 * 3.方法不同：run()/call()
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread().start();//怎么启动Callable

        MyThread thread=new MyThread();
        FutureTask futureTask=new FutureTask(thread);//适配类
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();//结果会被缓存，效率高

        Integer o=(Integer) futureTask.get();//获取Callable()的返回结果,这个get()方法可能会产生阻塞
        //或者使用异步通信
        System.out.println("o="+o);
    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("call()");//会打印几个call()
        //耗时的操作
        return 1024;
    }
}
