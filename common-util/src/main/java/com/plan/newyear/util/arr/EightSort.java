package com.plan.newyear.util.arr;

import com.plan.newyear.util.time.CalculateTime;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 八大排序方法：
 */
public class EightSort {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] arrs = new int[15000000];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = (int)(Math.random()*150000000);
        }
//        Date datei = new Date();
//        String formati = simpleDateFormat.format(datei);
//        System.out.println("插入排序前的时间"+formati);
//        insertSort(arrs);
//        Date date1i = new Date();
//        String format1i = simpleDateFormat.format(date1i);
//        System.out.println("插入排序后的时间"+format1i);
//        Date bdate = new Date();
//        String format2 = simpleDateFormat.format(bdate);
//        System.out.println("冒泡排序前的时间"+format2);
//        bubbleSort(arrs);
//        Date bdate1 = new Date();
//        String format3 = simpleDateFormat.format(bdate1);
//        System.out.println("冒泡排序后的时间"+format3);
//        Date date = new Date();
//        String format = simpleDateFormat.format(date);
//        System.out.println("选择排序前的时间"+format);
//        selectSort(arrs);
//        Date date1 = new Date();
//        String format1 = simpleDateFormat.format(date1);
//        System.out.println("选择排序后的时间"+format1);
//        List<String> daySub = CalculateTime.getDaySub(format, format1);
//        System.out.println(daySub);
        Date dates = new Date();
        String formats = simpleDateFormat.format(dates);
        System.out.println("shell排序前的时间"+formats);
        shellSorts(arrs);
        Date date1s = new Date();
        String format1s = simpleDateFormat.format(date1s);
        System.out.println("shell排序后的时间"+format1s);


    }

    //冒泡排序
    public static void bubbleSort(int[] arr){
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            if (flag == false){
                break;
            }else {
                flag = false;
            }
        }
    }

    //选择排序
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (min<arr[j]){
                    minIdex = j;
                    min = arr[j];
                }
            }
            if (minIdex != i){
                arr[minIdex] = arr[i];
                arr[i] = min;
            }
        }
    }
    
    //插入排序
    public static  void insertSort(int[] arr){
        int insertVal =0;
        int insertIndex=0;
        for (int i = 1; i <arr.length ; i++) {
             insertVal = arr[i];
             insertIndex = i-1;
            while (insertIndex>=0 && insertVal>arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex +1!=i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

    //希尔排序:交换法
    public static void shellSort(int[] arr){
        int temp = 0;
        for (int i = arr.length/2; i >0 ; i/=2) {
            for (int j = i; j < arr.length; j++) {
                for (int k = j-i; k >=0 ; k-=i) {
                    if (arr[k]>arr[k+i]){
                        temp = arr[k];
                        arr[k] = arr[k+i];
                        arr[k+i]=temp;
                    }
                }
            }
        }
    }

    //希尔排序：位移法
    public static void shellSorts(int[] arr){
        int insertVal = 0;
        int insertIndex = 0;
        for (int temp=arr.length/2 ; temp>0; temp /=2){
            for (int i = temp; i <arr.length ; i++) {
                insertIndex = i;
                insertVal = arr[insertIndex];
                if (arr[insertIndex] > arr[insertIndex-temp]){
                    while (insertIndex -temp >=0 && insertVal > arr[insertIndex-temp]){
                        arr[insertIndex] = arr[insertIndex-temp];
                        insertIndex -= temp;
                    }
                    arr[insertIndex] = insertVal;
                }
            }
        }
    }
}
