package com.zhuguang.jack.forkjoin;

public class MyTest {
    
    public static void main(String[] args) {
        int[] array = ArrayFactory.createArray();
        UserService user = new UserServiceImpl();
        
        int count = 0;
        
        Long t1 = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            if (user.execute(array, i)) {
                count++;
            }
        }
        Long t2 = System.currentTimeMillis();
        System.out.println("count大小为: " + count + "-----消耗时间为：" + (t2 - t1));
    }
    
}
