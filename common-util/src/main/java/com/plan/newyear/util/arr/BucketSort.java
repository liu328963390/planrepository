package com.plan.newyear.util.arr;


import com.plan.newyear.util.stack.SuffixStack;

import java.util.Arrays;

/**
 * 基数排序：
 */
public class BucketSort {
    public static void main(String[] args) {
//        int[] arr = {53,2,542,749,14,216,23023,12,2310921};
        //11*4/1024/1204/1024
        int[] arr = new int[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*1500000000);
        }
        radixSort(arr);
        System.gc();
    }

    //基数排序方法
    public static void radixSort(int[] arr){
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];
        int max = arr[0];//假设第一个数就是最大数
        for (int i = 1 ; i <arr.length ; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max+"").length();
        for (int m = 0,n=1; m < maxLength; m++,n*=10) {
            for (int i = 0; i < arr.length; i++) {
                //取出每个元素的个位数
                int digitOfElement = arr[i]/n%10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //遍历每1个桶，并将桶中是数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0){
                    //循环该桶，即第k个桶(即第k个一维数组），放入原数组
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementCounts[k]置0
                bucketElementCounts[k] = 0;
            }
            System.out.println("第"+(m+1)+"轮，对个位数的数组是："+ Arrays.toString(arr));
        }
        /*
        //第1轮（针对每个元素的个位数进行排序处理）
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组0-9的数
        //说明：
        //二维数组包含10个一维数组
        //为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定义为arr.length
        //明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
        //bucketElementCounts[0],记录的就是bucket[0]桶的放入数据的个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的个位数
            int digitOfElement = arr[i]%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        int index = 0;
        //遍历每1个桶，并将桶中是数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0){
                //循环该桶，即第k个桶(即第k个一维数组），放入原数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第1轮处理后，需要将每个bucketElementCounts[k]置0
            bucketElementCounts[k] = 0;
        }

        System.out.println("第1轮，对个位数的数组是："+ Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的十位数
            int digitOfElement = arr[i]/10%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每1个桶，并将桶中是数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0){
                //循环该桶，即第k个桶(即第k个一维数组），放入原数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;
        }
        System.out.println("第2轮，对个位数的数组是："+ Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的百位数
            int digitOfElement = arr[i]/100%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每1个桶，并将桶中是数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0){
                //循环该桶，即第k个桶(即第k个一维数组），放入原数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;
        }
        System.out.println("第3轮，对个位数的数组是："+ Arrays.toString(arr));*/
    }
}
