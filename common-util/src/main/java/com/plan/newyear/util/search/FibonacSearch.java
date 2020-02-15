package com.plan.newyear.util.search;

import com.plan.newyear.util.arithmetic.DataSearch;

import java.util.Arrays;

/**
 * 斐波拉契查找：
 */
public class FibonacSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,20,89,1000,1234};
        System.out.println(Arrays.toString(DataSearch.gloryArray()));
        System.out.println(DataSearch.gloryValueSearch(arr,89));
    }

    //因为后面我们mid=low+F(k-1)-1,需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归方法得到一个婓波那契数列
    public static int[] fibo(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <maxSize ; i++) {
            f[i] = f[i-1]+f[i-2];
        }
        return f;
    }

    //编写斐波那契查找算法
    /**
     * 使用非递归的方式编写算法
     * @param arr 数组
     * @param findVal 我们需要查找的关键码（值）
     * @return 返回对应的下标，如果没有-1
     */
    public static int fiboSearch(int[] arr,int findVal){
        int low = 0;
        int high = arr.length-1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int[] fibo = fibo();//获取斐波那契数列
        //获取斐波那契分割数值的下标
        while (high > fibo[k]-1){
            k++;
        }
        //因为f[k]值可能大于我们数组arr的长度,因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        int[] temp = Arrays.copyOf(arr,fibo[k]);//不足的部分会使用0填充
        //实际上需要使用arr数组最后的数填充temp
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while来循环处理，找到我们的数findVal
        while (low<=high){//只要这个条件满足，就可以找
            mid = low + fibo[k-1]-1;
            if (findVal<temp[mid]){//说明应该继续向数组的前面查找（左边）
                high = mid -1;
                //说明：1。全部元素 = 前面的元素 + 后边的元素
                //2。f[k] = f[k-1]+f[k-2]
                //3。因为前面有f[k-1]个元素，所以可以继续拆分 f[k-1]=f[k-2]+f[k-3],即在f[k-1]的前面继续查找，k--
                //即下次循环mid = f[k-1-1]-1
                k --;
            }else if (findVal>temp[mid]){//说明应该继续向数组的后面查找（右边）
                low = mid + 1;
                //说明：1。全部元素 = 前面的元素 + 后边的元素
                //2。f[k] = f[k-1]+f[k-2]
                //3。因为后面我们有f[k-2]个元素，所以可以继续拆分 f[k-2]=f[k-3]+f[k-4],即在f[k-2]的前面进行查找 k -= 2
                //即下次循环mid = f[k-1-2]-1
                k -= 2;
            }else {
                //需要确定，返回的是哪个下标
                if (mid<=high){
                    return  mid;
                }else {
                    return high;
                }
            }

        }
        return -1;
    }
}
