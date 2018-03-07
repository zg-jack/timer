package com.zhuguang.jack.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<Integer> {
    
    private int[] array;
    
    private int begin;
    
    private int end;
    
    private static int subArrayLength = 10;
    
    private UserService user;
    
    public ForkJoin(int[] array, int begin, int end, UserService user) {
        this.array = array;
        this.begin = begin;
        this.end = end;
        this.user = user;
    }
    
    protected Integer compute() {
        
        if (end - begin < subArrayLength) {
            int count = 0;
            for (int i = begin; i <= end; i++) {
                if (user.execute(array, i)) {
                    count++;
                }
            }
            return count;
        }
        else {
            int mid = (begin + end) / 2;
            ForkJoin leftTask = new ForkJoin(array, begin, mid, user);
            ForkJoin rightTask = new ForkJoin(array, mid + 1, end, user);
            this.invokeAll(leftTask, rightTask);
            return leftTask.join() + rightTask.join();
        }
    }
    
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] array = ArrayFactory.createArray();
        UserService user = new UserServiceImpl();
        
        ForkJoin task = new ForkJoin(array, 0, array.length - 1, user);
        new Thread(task.new MyThread(task, pool)).start();
        Long t1 = System.currentTimeMillis();
        Integer count = pool.invoke(task);
        Long t2 = System.currentTimeMillis();
        System.out.println("count大小为: " + task.join() + "-----消耗时间为："
                + (t2 - t1));
        System.out.println(count);
        
    }
    
    class MyThread implements Runnable {
        ForkJoin task;
        
        ForkJoinPool pool;
        
        public MyThread(ForkJoin task, ForkJoinPool pool) {
            this.task = task;
            this.pool = pool;
        }
        
        @Override
        public void run() {
            while (!task.isDone()) {
                monitorPool(pool);
            }
            //        pool.shutdown();
            monitorPool(pool);
        }
        
    }
    
    private static void monitorPool(ForkJoinPool pool) {
        System.out.println("线程池中线程数量：" + pool.getPoolSize());
        System.out.println("活跃线程数量，执行任务线程数量：" + pool.getActiveThreadCount());
        System.out.println("没有被阻塞正在工作的线程数量：" + pool.getRunningThreadCount());
        System.out.println("已经提交给池还没有开始执行任务的线程数量："
                + pool.getQueuedSubmissionCount());
        System.out.println("已经提交给池开始执行任务的线程数量：" + pool.getQueuedTaskCount());
        System.out.println("线程偷取任务数：" + pool.getStealCount());
        System.out.println("线程池是否终止：" + pool.isTerminated());
    }
}
