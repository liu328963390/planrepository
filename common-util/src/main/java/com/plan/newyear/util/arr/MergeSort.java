package com.plan.newyear.util.arr;

import java.util.Arrays;

/**
 * 归并排序：
 */
public class MergeSort {
    public static void main(String[] args) {
        int [] arr = {8,4,5,7,1,3,6,2};
        int temp[] = new int[arr.length];//归并排序需要一个额外空间
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    //分+合法
    public static void mergeSort(int [] arr, int left,int right,int[] temp){
        if (left < right){
            int mid = (left + right)/2;//中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //到合并
            merge(arr,left,right,mid,temp);
        }
    }

    //合并的方法
    /**
     * @param arr 待排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param right 右边索引
     * @param mid 中间索引
     * @param tem 待中转的数组
     */
    public static void merge(int[] arr,int left,int right,int mid,int[] tem){
        int i = left;//初始化i,左边有序序列的初始索引
        int j = mid + 1;//初始化j,右边有序序列的初始索引
        int t = 0;//指向tem数组的当前索引

        //1。先把左右两边(有序)的数据按照规则填充到tem数组，直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right){//继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素，即将左边的当前元素，拷贝到tem数组，然后t、i都往后移，
            if (arr[i] <= arr[j]){
                tem[t] = arr[i];
                t += 1;
                i +=1;
            }else {//反之，将右边有序序列的元素，填充到tem数组
                tem[t] = arr[j];
                t +=1;
                j +=1;
            }
        }
        //2。把有剩余数据的一边的数据依次全部填充到tem中
        while (i <= mid){//左边的有序序列还有剩余的元素，就全部填充到tem中
            tem[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right){
            tem[t] = arr[j];
            t += 1;
            j += 1;
        }
        //3。将tem数组的元素拷贝到arr数组中
        //注意：并不是每次都拷贝所有
        t = 0;
        int templeft = left;
        //第一次合并时，templeft=0.right =1 ;第2次 templeft=2,right=3
        //最后一次，templeft=0,right=7
        while (templeft <= right){
            arr[templeft] = tem[t];
            t += 1;
            templeft += 1;
        }
    }
}
