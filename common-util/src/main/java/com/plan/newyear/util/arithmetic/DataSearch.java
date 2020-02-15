package com.plan.newyear.util.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSearch {
    public static int maxSize = 45;

    /**
     * 插值查找算法：条件是有序的从小到大排列
     * @param arr 原始数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 需要查找的值
     * @return 如果有就返回下标，没有就返回-1
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        if (left>right || findVal<arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }
        int mid = left+(left-right)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if (findVal>midVal){
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

    /**
     * 插值查找算法：条件是有序的从小到大排列
     * @param arr 原始数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 需要查找的数
     * @return 如果有就返回所有的下标，没有就返回null
     */
    public static List insertValue(int[] arr,int left,int right,int findVal){
        if (left>right || findVal<arr[0] || findVal>arr[right]){
            return new ArrayList();
        }
        int mid = left + (right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if (findVal>midVal){
            return insertValue(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return insertValue(arr,left,mid-1,findVal);
        }else {
            List<Integer> list = new ArrayList<>();
            int temp = mid-1;
            while (true){
                if (temp<0 || arr[temp] != findVal){
                    break;
                }
                list.add(temp);
                temp -=1;
            }
            list.add(mid);
            temp = mid +1;
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

    /**
     * 二分查找算法：条件是有序的从大到小排列
     * @param arr 原始数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 需要查找的值
     * @return 如果查找到就返回下标，没有查找到就返回-1
     */
    public static int binaryValueSearch(int[] arr,int left,int right,int findVal){
        if (left>right){
            return -1;
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal>midVal){
            return binaryValueSearch(arr,left,mid-1,findVal);
        }else if (findVal<midVal){
            return binaryValueSearch(arr,mid+1,right,findVal);
        }else {
            return mid;
        }
    }

    /**
     * 二分查找算法：条件是有序的从小到大排列
     * @param arr 原始数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 需要查找的值
     * @return 如果有就返回所有，没有就返回null
     */
    public static List binaryValue(int[] arr,int left,int right,int findVal){
        if (left>right){
            return new ArrayList();
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal>midVal){
            return binaryValue(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return binaryValue(arr,left,mid-1,findVal);
        }else {
            List<Integer> list = new ArrayList<>();
            int temp = mid-1;
            while (true){
                if (temp<0 || arr[temp] != findVal){
                    break;
                }
                list.add(temp);
                temp -= 1;
            }
            list.add(mid);
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

    /**
     * 斐波那契查找算法：条件是有序的从小到大排列的数组
     * @param arr 原始数组
     * @param findVal 需要查找的值
     * @return 如果有就返回下标，没有就返回-1；
     */
    public static int gloryValueSearch(int[] arr,int findVal){
        int low = 0;
        int high = arr.length-1;
        int k = 0;
        int mid = 0;
        int[] F = gloryArray();
        while (high>F[k]-1){
            k++;
        }
        int[] temp = Arrays.copyOf(arr,F[k]);
        for (int i = high+1; i <temp.length ; i++) {
            temp[i] = arr[high];
        }
        while (low<=high){
            mid = low+F[k-1]-1;
            if (findVal<temp[mid]){
                high = mid - 1;
                k--;
            }else if (findVal>temp[mid]){
                low = mid + 1;
                k -= 2;
            }else {
                if (mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }

    /**
     * 定义一个斐波那契（黄金分割）数组
     * @return 返回斐波那契数组
     */
    public static int[] gloryArray(){
        int[] ints = new int[maxSize];
        for (int i = 2; i < maxSize; i++) {
            ints[0] = 1;
            ints[1] = 1;
            ints[i] = ints[i-1] + ints[i-2];
        }
        return ints;
    }
}
