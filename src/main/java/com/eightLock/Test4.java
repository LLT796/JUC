package com.eightLock;

import java.util.concurrent.TimeUnit;

/**
 * 7,一个静态同步方法，一个普通同步方法：先发短信还是先打电话？  打电话
 * 8，两个对象，一个静态同步方法，一个普通同步方法：先发短信还是先打电话？     打电话
 */

public class Test4 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(()->{phone1.sendSms();},"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{phone2.call();},"B").start();
    }
}

class Phone4{
    //静态同步方法，锁的是Class类模板
    public static synchronized void sendSms()
    {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("发短信");
    }
    //普通同步方法，锁的调用者
    public synchronized void call()
    {
        System.out.println("打电话");
    }
}