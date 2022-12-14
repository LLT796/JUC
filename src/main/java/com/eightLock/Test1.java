package com.eightLock;

import java.util.concurrent.TimeUnit;

/**
 * 八锁：关于锁的八个问题
 * 1：标准情况下，先发短信还是打电话？   发短信
 * 2：两个对象，先发短信还是打电话？    打电话
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{phone1.sendSms();},"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{phone2.call();},"B").start();
    }
}

class Phone{
    //synchronized锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁先执行
    public synchronized void sendSms()
    {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("发短信");
    }
    public synchronized void call()
    {
        System.out.println("打电话");
    }
}
