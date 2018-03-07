package com.zhuguang.jack.forkjoin;

import java.util.Random;

public class ArrayFactory {
    
    public static int arrayLength = 20000;
    
    public static int finddata = 3456;
    
    public static int[] createArray() {
        
        int[] array = new int[arrayLength];
        Random r = new Random();
        
        for (int i = 0; i < arrayLength; i++) {
            array[i] = r.nextInt(arrayLength * 4);
        }
        return array;
    }
    
    public static void main(String[] args) {
        int[] array = createArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
