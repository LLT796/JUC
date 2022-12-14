package com.eightLock;

import java.util.concurrent.TimeUnit;

/**
 * 5,增加两个静态方法:先打电话还是先发短信？   发短信
 * 5，两个静态对象,两个同步静态方法，先打电话还是先发短信？    发短信
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();
        new Thread(()->{phone1.sendSms();},"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{phone2.call();},"B").start();
    }
}

//Phone3唯一一个Class对象
class Phone3
{
    //synchronized锁的对象是方法的调用者
    //static静态方法，类一加载就有了，Class模板，锁的是Class
    public static synchronized void sendSms()
    {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("发短信");
    }
    public static synchronized void call()
    {
        System.out.println("打电话");
    }
}
