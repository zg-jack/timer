package com.zhuguang.jack.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinAsyn {
    
    private static class innerTask extends RecursiveAction {
        
        /** 
         *  往期视频加瑶瑶老师QQ：2483034688
         *  Jack老师QQ： 2943489129
         *  时间   ：     2018年2月2日 下午3:21:51 
         *  作者   ：   烛光学院【Jack老师】
         */
        
        private static final long serialVersionUID = 111144444444441L;
        
        private int[] array;
        
        private int begin;
        
        private int end;
        
        private static int subArrayLength = 100;
        
        private UserService user;
        
        public innerTask(int[] array, int begin, int end, UserService user) {
            this.array = array;
            this.begin = begin;
            this.end = end;
            this.user = user;
        }
        
        @Override
        protected void compute() {
            if (end - begin < subArrayLength) {
                int count = 0;
                for (int i = begin; i <= end; i++) {
                    if (user.execute(array, i)) {
                        count++;
                    }
                }
            }
            else {
                int mid = (begin + end) / 2;
                innerTask leftTask = new innerTask(array, begin, mid, user);
                innerTask rightTask = new innerTask(array, mid + 1, end, user);
                this.invokeAll(leftTask, rightTask);
            }
            
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        int[] array = ArrayFactory.createArray();
        UserService user = new UserServiceImpl();
        
        innerTask task = new innerTask(array, 0, array.length - 1, user);
        Long t1 = System.currentTimeMillis();
        pool.execute(task);
        Long t2 = System.currentTimeMillis();
        //        if (task.isCompletedNormally()) {
        //            System.out.println("count大小为: " + task.join() + "-----消耗时间为："
        //                    + (t2 - t1));
        //        }
        
    }
}
