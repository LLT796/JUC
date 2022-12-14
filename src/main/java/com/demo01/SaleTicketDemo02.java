package com.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket2.sale();},"A").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket2.sale();},"B").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket2.sale();},"C").start();
    }
}

class Ticket2
{
    private int number=30;

    //卖票的方式
    public void sale()
    {
        Lock lock=new ReentrantLock();
        lock.lock();//上锁
        try {
            if(number>0)
            {
                System.out.println(Thread.currentThread().getName()+"卖出了第"+(number--)+"张票，剩下："+number+"张票");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
}
