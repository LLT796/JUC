package com.pool;

import java.util.concurrent.*;

/**
 *拒绝策略：
 *(1)new ThreadPoolExecutor.AbortPolicy():银行满了，还有人进来，不处理这个人，抛出异常
 *(2)new ThreadPoolExecutor.CallerRunsPolicy():哪里来的去哪里
 *(3)new ThreadPoolExecutor.DiscardPolicy():队列满了，丢掉任务，不会抛出异常
 *(4)new ThreadPoolExecutor.DiscardOldestPolicy():队列满了，尝试去和最早的竞争，也不会抛出异常
 */
public class Demo02 {
    public static void main(String[] args) {
        //自定义线程池
        ExecutorService threadpool=new ThreadPoolExecutor(2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
                );

        /**
         * 最大线程数定义：
         * 1.CPU密集型：几核就是几，可以保持CPU的效率最高
         * 2.IO密集型： 判断程序中十分消耗IO的线程
         */
        try {
            for (int i = 1; i <= 25; i++) {
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadpool.shutdown();
        }
    }
}
