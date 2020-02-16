package com.plan.newyear.util.arr;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int arr[] = {4,6,8,5,9};
        heapSort(arr);
    }

    //写一个堆排序的方法
    public static void heapSort(int[] arr){
        int temp = 0;
        System.out.println("堆排序~~~~");
        //将无序序列构建成一个大顶堆或小顶堆
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }

        for (int i = arr.length-1; i >=0; i--) {
            //交换
            temp = arr[i];
            arr[i] = arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,i);
        }
        System.out.println(Arrays.toString(arr));
        //分布完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println(Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
//        System.out.println(Arrays.toString(arr));
    }

    //将一个数组（二叉树），调整为一个大顶堆

    /**
     * 功能：完成将以i对应的非叶子节点的树调整成大顶堆
     * 举例：int arr[] = {4,6,8,5,9};==> i=1 ==>adjustHeap ==>得到{4，9，8，5，6}
     * 再次调用adjustHeap 传入 i=0 ==>调为{4，9，8，5，6}  ==> 得到{9,6,8,5,4}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点的在数组中的索引
     * @param lenght 表示对多少个元素进行调整，lenght是在逐渐减少
     */
    public static void adjustHeap(int[] arr,int i,int lenght){
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //说明：k = i*2+1 k是i节点的左子节点
        for (int k = i*2+1; k < lenght; k=k*2+1) {
            if (k+1<lenght && arr[k] <arr[k+1]){//说明左子节点的值小于右子节点的值
                k++;//k就指向右子节点
            }
            if (arr[k] > temp){//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//i指向k,继续循环比较
            }else {
                break;//
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值放在了最顶上（局部大顶堆）
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
