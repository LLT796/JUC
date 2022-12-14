package com.pc;

/**
 * 线程之间的通信问题：生产者和消费者问题！ 等待唤醒，通知唤醒
 * 线程交替执行   A   B操作同一个变量 num=0
 * A num+1
 * B num-1
 */

public class A {
    public static void main(String[] args) {
        Data data = new Data();

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
            }
        },"C").start();
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

//判断等待、业务、通知
class Data  //数字资源类
{
    private int number=0;

    //+1
    public synchronized void increment() throws InterruptedException {
        /**
         * 这里只能用while不能用if的原因：
         * 用if判断的话，唤醒后线程会从wait之后的代码开始运行，但是不会重新判断if条件，直接继续运行if代码块之后的代码
         * 而如果使用while的话，也会从wait之后的代码运行，但是唤醒后会重新判断循环条件
         * 如果不成立再执行while代码块之后的代码块，成立的话继续wait。
         */
        while(number!=0)
        {
            //等待
            this.wait();
        }
        number++;
        //通知其他线程，我+1完成了
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        this.notifyAll();
    }

    //-1
    public synchronized  void decrement() throws InterruptedException {
        while(number==0)
        {
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我-1完成了
        this.notifyAll();
    }
}
