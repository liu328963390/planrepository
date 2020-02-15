package com.plan.newyear.util.search;

/**
 * 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int [] arr = {23,45,53,2,4};
        int serach = seqSerach(arr, 49);
        if (serach == -1){
            System.out.println("未找到");
        }else {
            System.out.println("找到了，下标为"+(serach+1));
        }

    }

    public static int seqSerach(int [] arr, int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
