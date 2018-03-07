package com.zhuguang.jack.scheduledExecutor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo implements Runnable {
    
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static void main(String[] args) {
        ScheduledExecutorService schedules = Executors.newScheduledThreadPool(10);
        
        Calendar currentTime = Calendar.getInstance();
        long currentTimeLong = currentTime.getTime().getTime();
        System.out.println("当前时间：" + sdf.format(currentTime.getTime()));
        Calendar nextExecutorTime = Calendar.getInstance();
        nextExecutorTime.set(Calendar.DAY_OF_WEEK, 6);
        nextExecutorTime.set(Calendar.HOUR_OF_DAY, 15);
        nextExecutorTime.set(Calendar.MINUTE, 10);
        nextExecutorTime.set(Calendar.SECOND, 0);
        
        long nextExecutorTimeLong = nextExecutorTime.getTime().getTime();
        long delay = nextExecutorTimeLong - currentTimeLong;
        long period = 7 * 24 * 60 * 60 * 1000;
        
        schedules.scheduleAtFixedRate(new ScheduledExecutorDemo(),
                delay,
                period,
                TimeUnit.MILLISECONDS);
    }
    
    public void run() {
        System.out.println("task executor" + sdf.format(new Date()));
    }
}
