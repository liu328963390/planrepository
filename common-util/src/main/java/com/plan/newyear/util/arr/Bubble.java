package com.plan.newyear.util.arr;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int[] arr = {98,21,87,-90,42,-78};
        bubleSort(arr);
        System.out.printf("排序后的数组为"+ Arrays.toString(arr));
    }

    public static void bubleSort(int[] arr){
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j]<arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (flag == false){
                break;
            }else {
                flag = false;
            }
        }
    }
}
