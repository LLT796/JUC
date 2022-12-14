package com.blockingqueue;

import com.pc.A;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    /**
     * 抛出异常
     */
    public static void test1()
    {
        //队列的大小
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        System.out.println(arrayBlockingQueue.element());//返回队首元素
        //System.out.println(arrayBlockingQueue.add("c"));//抛出异常
        System.out.println("==============================");
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        //System.out.println(arrayBlockingQueue.remove());
    }

    /**
     * 不抛出异常，有返回值
     */
    public static void test2()
    {
        ArrayBlockingQueue blockingQueue=new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //System.out.println(blockingQueue.offer("d"));//不抛出异常，false
        System.out.println(blockingQueue.peek());//返回队首元素
        System.out.println("=========================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //System.out.println(blockingQueue.poll());//null
    }

    /**
     * 一直阻塞,等待
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue=new ArrayBlockingQueue(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //blockingQueue.put("d");
        System.out.println("=============================");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //System.out.println(blockingQueue.take());
    }

    /**
     * 超时等待
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue=new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //blockingQueue.offer("d",2, TimeUnit.SECONDS);

        System.out.println("=============================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //blockingQueue.poll(2,TimeUnit.SECONDS);
    }
}
