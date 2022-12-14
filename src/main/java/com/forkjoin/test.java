package com.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test1();//49682

        //test2();//5977

        test3();//96
    }

    //普通方法
    public static void test1()
    {
        long start=System.currentTimeMillis();
        Long sum=0L;
        for(Long i=1L;i<=10_0000_00000L;i++)
        {
            sum+=i;
        }
        long end=System.currentTimeMillis();
        System.out.println("sum="+sum+"时间： "+(end-start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();

        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Long> task=new ForkJoinDemo(0L,10_0000_0000L);
        ForkJoinTask<Long> submit=forkJoinPool.submit(task);//提交任务

        Long sum=submit.get();
        long end=System.currentTimeMillis();

        System.out.println("sum="+sum+"  时间： "+(end-start));
    }

    public static void test3()
    {
        //Stream并行流
        long start=System.currentTimeMillis();

        Long sum=LongStream.rangeClosed(0L,10_0000_0000).parallel().reduce(0,Long::sum);

        long end=System.currentTimeMillis();
        System.out.println("sum="+sum+"时间： "+(end-start));
    }
}
