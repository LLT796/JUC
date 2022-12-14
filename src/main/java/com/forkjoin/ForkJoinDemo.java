package com.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 如何使用forkjoin:
 * 1.forkjoin通过他来执行
 * 2.计算任务 forkjoinPool.execute(ForkJoinTask task)
 * forkjoinTask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    public ForkJoinDemo(Long start,Long end)
    {
        this.start=start;
        this.end=end;
    }
    private Long temp=10000L;//临界值


    //计算方法
    @Override
    protected Long compute() {
        if((end-start)<temp)
        {
            Long sum=0L;
            for (Long i=start;i<=end;i++)
            {
                sum+=i;
            }
            return sum;
        }
        else{
            //分支合并计算，forkjoin
            long mid=(start+end)/2;//中间值
            ForkJoinDemo task1=new ForkJoinDemo(start,mid);
            task1.fork();//拆分任务，把任务压入线程队列
            ForkJoinDemo task2=new ForkJoinDemo(mid+1,end);
            task2.fork();//拆分任务，把任务压入线程队列

            return task1.join()+task2.join();
        }
    }
}
