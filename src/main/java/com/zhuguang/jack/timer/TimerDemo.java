package com.zhuguang.jack.timer;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo extends TimerTask {
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public void run() {
        
        System.out.println("task execute!"
                + sdf.format(this.scheduledExecutionTime()));
        //        try {
        //            Thread.sleep(5000);
        //        }
        //        catch (InterruptedException e) {
        //            // TODO Auto-generated catch block
        //            e.printStackTrace();
        //        }
    }
    
    public static void main(String[] args) {
        Timer timer = new Timer();
        //        timer.schedule(new TimerDemo(), 1000, 1000);
        timer.scheduleAtFixedRate(new TimerDemo(), 1000, 1000);
        timer.scheduleAtFixedRate(new TimerDemo(), 1000, 1000);
    }
    
}
