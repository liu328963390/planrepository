package com.plan.newyear.util.arr;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int [] arr = {89,23,-238,87};
        insertSort(arr);
    }

    //插入排序
    public static void insertSort(int[] arr){

        //整理
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i-1;
            while (insertIndex>=0 && insertVal>arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            //判断是否需要进行赋值
            if (insertIndex+1 != i){
                arr[insertIndex+1] = insertVal;
            }

            System.out.println("第"+i+"轮插入的数据"+Arrays.toString(arr));
        }

        /*
        //逐步推导的方式
        //第一轮{89,23,-238,87} =>{23,89,-238,87}
        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1-1;//即arr[1]的前面这个数的下标
        //给insertVal找到插入的位置
        //insertIndex>=0 保证在给inserVal找插入位置不越界
        //insertVal < arr[insertIndex] 待插入的数还未找到插入位置
        //需要将arr[insertIndex]后移
        while (insertIndex>=0 && insertVal < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];//arr[insertIndex]后移
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex+1
        arr[insertIndex+1] = insertVal;
        System.out.println("第一轮插入的数据"+ Arrays.toString(arr));

        insertVal = arr[2];
        insertIndex = 2-1;//即arr[1]的前面这个数的下标
        //给insertVal找到插入的位置
        //insertIndex>=0 保证在给inserVal找插入位置不越界
        //insertVal < arr[insertIndex] 待插入的数还未找到插入位置
        //需要将arr[insertIndex]后移
        while (insertIndex>=0 && insertVal < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];//arr[insertIndex]后移
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex+1
        arr[insertIndex+1] = insertVal;
        System.out.println("第二轮插入的数据"+ Arrays.toString(arr));

        insertVal = arr[3];
        insertIndex = 3-1;//即arr[1]的前面这个数的下标
        //给insertVal找到插入的位置
        //insertIndex>=0 保证在给inserVal找插入位置不越界
        //insertVal < arr[insertIndex] 待插入的数还未找到插入位置
        //需要将arr[insertIndex]后移
        while (insertIndex>=0 && insertVal < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];//arr[insertIndex]后移
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex+1
        arr[insertIndex+1] = insertVal;
        System.out.println("第三轮插入的数据"+ Arrays.toString(arr));*/
    }
}
