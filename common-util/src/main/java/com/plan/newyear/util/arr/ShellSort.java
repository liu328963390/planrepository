package com.plan.newyear.util.arr;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        shellSorts(arr);
        System.out.println(Arrays.toString(arr));
    }

    //使用逐步推导编写shell排序
    //交换法
    public static void shellSort(int[] arr){
        int temp = 0;
        //根据下面的逐步分析，使用循环处理
        int count = 0;
        for (int gap = arr.length/2;gap>0;gap/=2){
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共gap组，第组有2个元素，步长gap
                for (int j = i-gap; j >=0 ; j-=gap) {
                    //如当前元素大于加上步长后的那个元素，说明需要进行交换
                    if (arr[j]>arr[j+gap]){
                        temp = arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            System.out.println("处理后的结果第"+(++count)+"轮结果是："+ Arrays.toString(arr));
        }

        /*
        //shell排序的第一轮排序
        //思路：第1轮排序是将10个数据分成了5组（arr.length)/2
        int temp = 0;
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，第组有2个元素，步长5
            for (int j = i-5; j >=0 ; j-=5) {
                //如当前元素大于加上步长后的那个元素，说明需要进行交换
                if (arr[j]>arr[j+5]){
                    temp = arr[j];
                    arr[j]=arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println("处理后的结果1"+ Arrays.toString(arr));

        //shell排序的第二轮排序
        //思路：第1轮排序是将10个数据分成了5组（arr.length)/2
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，第组有2个元素，步长5
            for (int j = i-2; j >=0 ; j-=2) {
                //如当前元素大于加上步长后的那个元素，说明需要进行交换
                if (arr[j]>arr[j+2]){
                    temp = arr[j];
                    arr[j]=arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println("处理后的结果2"+ Arrays.toString(arr));

        //shell排序的第三轮排序
        //思路：第1轮排序是将10个数据分成了5组（arr.length)/2
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，第组有2个元素，步长5
            for (int j = i-1; j >=0 ; j-=1) {
                //如当前元素大于加上步长后的那个元素，说明需要进行交换
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("处理后的结果3"+ Arrays.toString(arr));*/
    }

    //对交换式的shell排序进行优化，移位法
    public static void shellSorts(int[] arr){
        int j = 0;
        int tmep = 0;
        for (int h = arr.length/2; h >0 ; h/=2) {
            //从第i个元素开始，逐个对其所在的组进行直接插入排序
            for (int i = h; i <arr.length ; i++) {
                j = i;
                tmep = arr[j];
                if (arr[j]<arr[j-h]){
                    while (j-h>=0 && tmep<arr[j-h]){
                        //移动
                        arr[j]=arr[j-h];
                        j-=h;
                    }
                    arr[j]=tmep;
                }
            }


        }
    }

    public static void shellSort3(int[] arr){
        for (int gap= arr.length/2;gap>0;gap/=2){
            for (int i=gap;i<arr.length;i++){
                int j=i;
                int temp = arr[j];
                if (arr[j] < arr[j-gap]){
                    while (j-gap >= 0 && temp < arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j -=gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}
