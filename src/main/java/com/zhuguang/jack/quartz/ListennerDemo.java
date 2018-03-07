package com.zhuguang.jack.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class ListennerDemo implements JobListener {
    
    public String getName() {
        return "myListener";
    }
    
    public void jobExecutionVetoed(JobExecutionContext arg0) {
        // TODO Auto-generated method stub
        
    }
    
    public void jobToBeExecuted(JobExecutionContext arg0) {
        System.out.println("执行task之前调用！！");
    }
    
    public void jobWasExecuted(JobExecutionContext arg0,
            JobExecutionException arg1) {
        if (arg1 != null) {
            System.out.println("有异常时调用！！");
        }
        
    }
    
}
