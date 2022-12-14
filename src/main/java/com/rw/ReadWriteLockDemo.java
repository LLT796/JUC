package com.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 * 独占锁（写锁）一次只能被一个线程占有
 * 共享锁（读锁） 多个线程可以同时占有
 * 共享锁
 * 读-读  可以共存
 * 读-写  不能共存
 * 写-写  不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();
        //写入
        for (int i = 1; i <= 5; i++) {
            final int temp=i;//多线程里不能直接使用i
            new Thread(()->{
                myCache.put(temp+" ",temp+" ");
            },String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //读取
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.get(temp+" ");
            },String.valueOf(i)).start();
        }
    }
}

/**
 * 自定义缓存（加锁）
 */
class MyCacheLock{
    private volatile Map<String,Object> map=new HashMap<>();
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    //存,写的过程
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入成功！");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }
    //取，读的过程
    public void get(String key)
    {
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            Object o=map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取OK！");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }
}


/**
 * 自定义缓存（没有加锁）
 */
class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();

    //存,写的过程
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入成功！");
    }
    //取，读的过程
    public void get(String key)
    {
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o=map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取OK！");
    }
}
