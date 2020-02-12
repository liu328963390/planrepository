package com.plan.newyear.util.arr;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,39,108,1};
        System.out.println("排序前"+Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后"+Arrays.toString(arr));

        int[] arrs = new int[800000];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = (int)(Math.random()*800000);
        }
        System.out.println("排序前的时间");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        selectSort(arrs);
        Date date1 = new Date();
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间");
        System.out.println(format1);
    }

    //选择排序
    public static void selectSort(int[] arr){
        //使用逐步推导的方式来
        //第1轮
        //算法 先简单->做复杂，就可以把一个复杂的算法，拆分成简单的问题 ->逐步解决

        //在推导过程，发现规律
        for (int i = 0; i < arr.length-1; i++) {
            int minIdex = i;
            int min =arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (min>arr[j]){//说明指定的最小值，并不是最小
                    min = arr[j];//重置min
                    minIdex = j;
                }
            }
            if (minIdex != i) {
                //将最小值，放在arr[0],即交换
                arr[minIdex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第"+(i+1)+"轮~");
//            System.out.println(Arrays.toString(arr));
        }

        /*
        //推导过程
        //第一轮
        int minIdex = 0;
        int min =arr[0];
        for (int i = 0+1; i < arr.length; i++) {
            if (min>arr[i]){//说明指定的最小值，并不是最小
                min = arr[i];//重置min
                minIdex = i;
            }
        }
        if (minIdex != 0) {
            //将最小值，放在arr[0],即交换
            arr[minIdex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第一轮~");
        System.out.println(Arrays.toString(arr));

        //第二轮
        minIdex = 1;
        min =arr[1];
        for (int i = 1+1; i < arr.length; i++) {
            if (min>arr[i]){//说明指定的最小值，并不是最小
                min = arr[i];//重置min
                minIdex = i;
            }
        }
        if (minIdex != 1) {
            //将最小值，放在arr[0],即交换
            arr[minIdex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第二轮~");
        System.out.println(Arrays.toString(arr));

        //第三轮
        minIdex = 2;
        min =arr[2];
        for (int i = 2+1; i < arr.length; i++) {
            if (min>arr[i]){//说明指定的最小值，并不是最小
                min = arr[i];//重置min
                minIdex = i;
            }
        }
        if (minIdex != 2) {
            //将最小值，放在arr[0],即交换
            arr[minIdex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第三轮~");
        System.out.println(Arrays.toString(arr));*/
    }
}
