package com.zhuguang.jack.forkjoin;

import java.util.concurrent.TimeUnit;

public class UserServiceImpl implements UserService {
    
    @Override
    public boolean execute(int[] array, int index) {
        if (array[index] > ArrayFactory.finddata) {
            doSomething();
            return true;
        }
        else {
            return false;
        }
    }
    
    private void doSomething() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
