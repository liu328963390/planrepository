package com.plan.newyear.util.arr;

import com.plan.newyear.util.stack.SuffixStack;
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
            arrs[i] = (int)(Math.random()*1500000000);
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
//        Date dates = new Date();
//        String formats = simpleDateFormat.format(dates);
//        System.out.println("快速排序前的时间"+formats);
//        shellSorts(arrs);
//        Date date1s = new Date();
//        String format1s = simpleDateFormat.format(date1s);
//        System.out.println("快速排序后的时间"+format1s);
//        Date dateq = new Date();
//        String formatq = simpleDateFormat.format(dateq);
//        System.out.println("shell排序前的时间"+formatq);
//
//        quickSort(arrs,0,arrs.length-1);
//        Date date1q = new Date();
//        String format1q = simpleDateFormat.format(date1q);
//        System.out.println("shell排序后的时间"+format1q);

        Date dateqm = new Date();
        String formatqm = simpleDateFormat.format(dateqm);
        System.out.println("归并排序前的时间"+formatqm);
        int[] temp = new int[arrs.length];
       heapSort(arrs);
        Date date1qm = new Date();
        String format1qm = simpleDateFormat.format(date1qm);
        System.out.println("归并排序后的时间"+format1qm);
        System.gc();
        try {
            List<String> list = SuffixStack.doMatch("150000000*11*4/1024/1204/1024");
            SuffixStack.doCalc(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    //快速排序
    public static void quickSort(int [] arr,int left, int right){
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];
        int temp = 0;
        while (l < r){
            while (arr[l] < pivot){
                l +=1;
            }
            while (arr[r] > pivot){
                r -=1;
            }
            if (l >= r){
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot){
                r -=1;
            }
            if (arr[r] == pivot){
                l +=1;
            }
        }
        if (l == r){
            l += 1;
            r -= 1;
        }
        if (left < r){
            quickSort(arr,left,r);
        }
        if (right > l){
            quickSort(arr,l,right);
        }
    }

    //归并排序法
    //分与全

    /**
     *
     * @param arr 原始数组
     * @param left 左下标
     * @param right 右下标
     * @param temp 临时数组
     */
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,right,mid,temp);
        }
    }
    //合

    /**
     *
     * @param arr 原始数组
     * @param left 左下标
     * @param right 右下标
     * @param mid 中间下标
     * @param temp 临时数组
     */
    public static void merge(int[] arr,int left,int right,int mid,int[] temp){
        int i = left;
        int j = mid+1;
        int t = 0;
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i +=1;
            }else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        if (i <= mid){
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        if (j <= right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        t = 0;
        int templeft = left;
        if (templeft <= right){
            arr[templeft] = temp[t];
            templeft += 1;
            t += 1;
        }
    }

    //桶排序或者是基数排序法
    public static void bucketSort(int[] arr){
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max<arr[i]){
                max = arr[i];
            }
        }
        int maxLength = (max+"").length();
        for (int i = 0,n=1; i < maxLength; i++,n*=10) {
            for (int j = 0; j < arr.length; j++) {
                int bucketElement = arr[j]/n%10;
                bucket[bucketElement][bucketElementCounts[bucketElement]] = arr[j];
                bucketElementCounts[bucketElement]++;
            }
            int index = 0;
            for (int j = 0; j < bucketElementCounts.length; j++) {
                if (bucketElementCounts[j] != 0){
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                bucketElementCounts[j] = 0;
            }
        }

    }

    //堆排序
    public static void heapSort(int[] arr){
        int temp = 0;
        for (int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        for (int i=arr.length-1;i>=0;i--){
            temp = arr[i];
            arr[i] = arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,i);
        }
    }

    /**
     * 完成将以i对应的非叶子节点的树调整成大顶堆
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，lenght是在逐渐减少
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];
        for (int j = i*2+1;j<length;j=j*2+1){
            if (j+1<length && arr[j] < arr[j+1]){
                j++;
            }
            if (arr[j]>temp){
                arr[i] = arr[j];
                i=j;
            }else {
                break;
            }
        }
        arr[i] = temp;
    }
}
