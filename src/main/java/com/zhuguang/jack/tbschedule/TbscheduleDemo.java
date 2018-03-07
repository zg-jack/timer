package com.zhuguang.jack.tbschedule;

import java.util.Comparator;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import com.taobao.pamirs.schedule.TaskItemDefine;

public class TbscheduleDemo implements IScheduleTaskDealMulti<JSONObject> {
    
    public Comparator<JSONObject> getComparator() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<JSONObject> selectTasks(String taskParameter, String ownSign,
            int taskQueueNum, List<TaskItemDefine> taskItemList,
            int eachFetchDataNum) throws Exception {
        System.out.println("-----------------selectTasks--------------------");
        System.out.println("taskParameter=" + taskParameter + "--ownSign"
                + ownSign + "--taskQueueNum" + taskQueueNum
                + "--eachFetchDataNum" + eachFetchDataNum);
        if (taskItemList != null && taskItemList.size() > 0) {
            for (TaskItemDefine define : taskItemList) {
                System.out.println(define.getTaskItemId() + ":"
                        + define.getParameter());
            }
        }
        
        /**
         * taskParameter：对应控制台自定义参数，可自定义传入做逻辑上的操作

        taskQueueNum：对应控制台任务项数量

        taskItemList：集合中TaskItemDefine的id值对应任务项值，多线程处理时，根据任务项协调数据一致性和完整性

        eachFetchDataNum：对应控制台每次获取数量，由于子计时单元开始后，会不断的去取数据进行处理，直到取不到数据子计时才停止，等待下一个子计时开始。可以限制每次取数，防止一次性数据记录过大，内存不足。

        ownSign：环境参数，一般没什么用
         */
        return null;
    }
    
    public boolean execute(JSONObject[] arg0, String arg1) throws Exception {
        return false;
    }
    
}
