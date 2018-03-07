package com.zhuguang.jack.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzDemo implements Job {
    
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static void main(String[] args) throws ParseException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = new JobDetail("myJob", "myGroup",
                    QuartzDemo.class);
            jobDetail.getJobDataMap().put("school", "烛光");
            jobDetail.getJobDataMap().put("teaher", "jack");
            //            jobDetail.addJobListener("myListener");
            
            Trigger trigger = TriggerUtils.makeWeeklyTrigger(6, 15, 32);
            trigger.setName("mytrigger");
            trigger.setGroup("mytriggerGroup");
            trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));
            
            //            CronTrigger trigger = new CronTrigger();
            //            trigger.setName("mytrigger");
            //            trigger.setGroup("mytriggerGroup");
            //            trigger.setCronExpression("0 7 * * * ?");
            scheduler.scheduleJob(jobDetail, trigger);
            //            scheduler.addJobListener(new ListennerDemo());
            scheduler.start();
        }
        catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("task executor" + sdf.format(new Date()));
        throw new RuntimeException("xx");
    }
}
