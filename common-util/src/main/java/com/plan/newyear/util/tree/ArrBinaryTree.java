package com.plan.newyear.util.tree;

/**
 * 顺序遍历
 */
public class ArrBinaryTree{
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrayBinar arrayBinar = new ArrayBinar(arr);
        arrayBinar.preOrder();
    }

}

//编写一个ArrayBinary,实现顺序存储二叉树遍历
class ArrayBinar{
    private int[] arr;//存储数据的数组，数据节点的数组

    public ArrayBinar(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }
    //完成顺序存储二叉树的一个前序遍历
    /**
     *
     * @param index 表示数组的下标
     */
    public void preOrder(int index){
        //如果数组为空，或者arr.lenght = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if (index*2+1 < arr.length){
            preOrder(index*2 +1);
        }
        //向右递归遍历
        if (index*2+2 <arr.length){
            preOrder(index*2+2);
        }
    }
}