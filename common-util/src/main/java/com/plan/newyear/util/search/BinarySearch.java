package com.plan.newyear.util.search;

import com.plan.newyear.util.arithmetic.DataSearch;
import com.plan.newyear.util.arr.EightSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分查找:二分查找的前提是该数组是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {29,34,9,87,9,34,4,23,9,34,108};
        EightSort.bucketSort(arr);
        System.out.println(Arrays.toString(arr));
        int i = DataSearch.binaryValueSearch(arr, 0, arr.length-1, 9);
        System.out.println("result="+i);
        List<Integer> list = DataSearch.binaryValue(arr, 0, arr.length - 1, 34);
        System.out.println(list);
    }

    //二分查找算法

    /**
     *
     * @param arr 要查找的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，没有找到，就返回-1
     */
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        //当left>right时，说明递归整个数组，但是没有找到
        if (left>right){
            return -1;
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal>midVal){
            return binarySearch(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

    /**
     * 当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到
     * 思路分析：
     * 1。在找到mid索引值，不要马上返回
     * 2。向mid索引值的左边扫描，将所有的满足的相同的值的下标加入到集合ArrayList中
     * 3。向mid索引值的右边扫描，将所有的满足的相同的值的下标加入到集合ArrayList中
     * 4。将ArrayList进行返回
     */
    /**
     *
     * @param arr 原数组
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List binary(int[] arr, int left, int right, int findVal){
        if (left>right){
            return new ArrayList();
        }
        int mid = (left+right)/2;
        int minVal = arr[mid];
        if (findVal>minVal){
            return binary(arr,mid+1,right,findVal);
        }else if (findVal<minVal){
            return binary(arr,left,mid-1,findVal);
        }else {
            List<Integer> list = new ArrayList<>();
            //向mid索引值的左边扫描，将所有的满足的相同的值的下标加入到集合ArrayList中
            int temp = mid -1;
            while (true){
                if (temp < 0 || arr[temp] != findVal){//退出
                    break;
                }
                //否则就把temp放入到集合中
                list.add(temp);
                temp -=1;
            }
            list.add(mid);
            //向mid索引值的右边扫描，将所有的满足的相同的值的下标加入到集合ArrayList中
            temp = mid + 1;
            while (true){
                if (temp>arr.length-1 || arr[temp] != findVal){
                    break;
                }
                list.add(temp);
                temp += 1;
            }
            return list;
        }
    }

}
