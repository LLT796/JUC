package com.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B {
    public static void main(String[] args) {
        Data2 data=new Data2();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
            try {
                data.increment();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }},"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"D").start();
    }
}

class Data2
{
    private int number=0;

    Lock lock =new ReentrantLock();
    Condition condition=lock.newCondition();

    //+1操作
    public void increment() throws InterruptedException {
        lock.lock();//上锁
        try {
            while(number!=0)
            {
                //等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"->"+number);
            //通知其他线程+1完成
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    //-1操作
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while(number==0)
            {
                //等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"->"+number);
            //通知其他线程
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
