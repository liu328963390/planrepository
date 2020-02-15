package com.plan.newyear.util.search;

import java.util.Arrays;

/**
 * 插值算法
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i*5;
        }
        System.out.println(Arrays.toString(arr));
        int i = insertValue(arr, 0, arr.length-1, 60);
        System.out.println(i);
    }

    /**
     *
     * @param arr 原始数组 从小到大排列
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找值
     * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
     */
    public static int insertValue(int[] arr,int left,int right,int findVal){
        //indVal<arr[0] || findVal>arr[arr.length-1] 不但起到优化的作用，而且必须需要，否则我们得到的mid可能越界
        if (left>right || findVal<arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }
        //求出mid,自适应算法
        int mid = left +(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if (findVal>midVal){
            return insertValue(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return insertValue(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }
}
