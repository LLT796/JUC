package com.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 集齐七颗龙珠召唤神龙
         */
        //召唤龙珠的线程
        CyclicBarrier barrier=new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功！");
        });

        for (int i = 1; i <= 7; i++) {
            //lambda能直接操作到i吗
            final  int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集了"+temp+"颗龙珠！");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
