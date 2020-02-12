package com.plan.newyear.util.arr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        double arrs[] = {-3.9023,10,24,-9,48.23,15};
        bubble(arrs);
        int arr[] = {-3,10,24,-9,48,15};
        bubleSort(arr);
        System.out.println("排序后的数组为"+Arrays.toString(arrs));
        System.out.println("排序后的数组为"+Arrays.toString(arr));
        /*
        int [] arr = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        System.out.printf("排序前的时间为"+format);
        bubleSort(arr);
        Date date1 = new Date();
        String format1 = dateFormat.format(date1);
        System.out.printf("排序后的时间为"+format1);

       /* boolean flag = false;//标识符，标识变量，表示是否进行过交换
        //冒泡排序，就是将最大的数排在最后，冒泡排序的时间复杂度就O(n^2)
        for (int j = 0; j <arr.length-1 ; j++) {
            int temp = 0;//临时变量
            for (int i = 0; i < arr.length-1-j; i++) {
                //如果前面的数比后面的数大，就进行交换
                if (arr[i] > arr[i+1]){
                    flag = true;
                    temp = arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;
                }
            }
            if (flag == false){//说明在一趟排序中，一次都没有发生过
                break;
            }else {
                flag=false;//重置flag,进行下次判断
            }
            System.out.printf("第%d趟排序后的数组\n",j+1);
            System.out.println(Arrays.toString(arr));
        }


        */

        /*
        //第一趟排序，就是将最的数排在倒数第一位
        int temp = 0;//临时变量
            for (int i = 0; i < arr.length-1-j; i++) {
                //如果前面的数比后面的数大，就进行交换
                if (arr[i] > arr[i+1]){
                    temp = arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;
                }
            }
            System.out.printf("第%d趟排序后的数组\n",j+1);
            System.out.println(Arrays.toString(arr));

        //第二趟排序，就是将第二大的数排在倒数第二位
        for (int i = 0; i < arr.length-1-1; i++) {
            //如果前面的数比后面的数大，就进行交换
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=temp;
            }
        }
        System.out.println("第二趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第三趟排序，就是将第二大的数排在倒数第二位
        for (int i = 0; i < arr.length-1-2; i++) {
            //如果前面的数比后面的数大，就进行交换
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=temp;
            }
        }
        System.out.println("第三趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第四趟排序，就是将第二大的数排在倒数第二位
        for (int i = 0; i < arr.length-1-3; i++) {
            //如果前面的数比后面的数大，就进行交换
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=temp;
            }
        }
        System.out.println("第四趟排序后的数组");
        System.out.println(Arrays.toString(arr));
        //第五趟排序，就是将第二大的数排在倒数第二位
        for (int i = 0; i < arr.length-1-4; i++) {
            //如果前面的数比后面的数大，就进行交换
            if (arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=temp;
            }
        }
        System.out.println("第五趟排序后的数组");
        System.out.println(Arrays.toString(arr));*/
    }

    //将前面的冒泡排序封装为一个方法
    public static void bubleSort(int[] arr){
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = 0;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j]<arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    flag = true;
                }
            }
            if (flag == false){
                break;
            }
                flag = false;

        }
    }

    //手写冒泡排序的方法
    public static  void bubble(double[] arr){
        boolean flag = false;
        double temp = 0.0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    flag = true;
                }
            }
            if (flag == false){
                break;
            }
                flag = false;

        }
    }
}
