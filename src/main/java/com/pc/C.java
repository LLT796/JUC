package com.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class C {
    public static void main(String[] args) {
        //操作同一个资源类
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"C").start();
    }
}

class Data3{    //资源类
    private Lock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();//同步监视器
    private Condition condition2=lock.newCondition();//同步监视器
    private Condition condition3=lock.newCondition();//同步监视器
    private int number=1;   //1A    2B      3C

    public void printA(){
        lock.lock();
        try {
            //业务，判断->执行->通知
            while(number!=1)
            {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"->"+"A");
            number=2;
            //通知其他线程
            condition2.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
    public void printB(){
        lock.lock();
        try {
            //业务，判断->执行->通知
            while(number!=2)
            {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"->"+"B");
            number=3;
            condition3.signal();//唤醒线程三
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            //业务，判断->执行->通知
            while(number!=3)
            {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"->"+"C");
            number=1;
            condition1.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    //生产线：下单->支付->交易->物流
}
