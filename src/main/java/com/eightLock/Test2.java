package com.eightLock;

import java.util.concurrent.TimeUnit;

/**
 * 第3个问题：增加一个普通方法后，先答应发短信还是hello？   普通方法
 * 4:两个对象，两个同步方法，先打电话还是先发短信？
 */
public class Test2 {
    public static void main(String[] args) {
        //两个对象
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        new Thread(()->{phone1.sendSms();},"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{phone2.call();},"B").start();
    }
}

class Phone2{
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

    //没有锁，不是同步方法，不受锁的影响
    public void hello()
    {
        System.out.println("Hello");
    }
}
