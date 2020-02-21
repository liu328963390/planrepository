package com.plan.newyear.util.algorithm;

import java.util.Arrays;

/**
 * 暴力算法：
 * KMP算法：
 */
public class ViolenceMatch{
    public static void main(String[] args) {
        String str1 = "aoifpjpjali kepjpjalipapjalipakeasd pjafija  wewe  alsdf";
        String str2 = "pjalipake";
//        String str2="AAAB";
        int i = violenceSearch(str1, str2);
        System.out.println(i);
        int[] ints = partValue(str2);
        System.out.println(Arrays.toString(ints));
        int i1 = kmpSearch(str1, str2, partValue(str2));
        System.out.println(i1);
    }



    /**
     * KMP算法：搜索算法
     * @param str1 原字符串
     * @param str2 子串
     * @param next 子串所对应的部分匹配表
     * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历str1
        for (int i = 0, j = 0; i <str1.length() ; i++) {
            //需要处理str1.charAt(i) ！= str2.charAt(j),调整j的大小
            while (j>0 && str1.charAt(i) != str2.charAt(j)){//核心点
                j=next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }



    /**
     * 获取到一个字符串（子串）的部分匹配值表
     * @param str 子串
     * @return 返回一个数组
     */
    private static int[] partValue(String str){
        //创建一个数组保存部分匹配值
        int[] next = new int[str.length()];
        next[0] = 0;//如果字符串长度为1，它的部分匹配值就是0
        for (int i = 1,j = 0; i <str.length() ; i++) {
            //当str.charAt(i) ！= str.charAt(j)，我们需要从next[j-1]获取新的j;同时还要满足，直到发现str.charAt(i) == str.charAt(j)有满足时才退出
            //这是KMP算法的核心点
            while (j>0 && str.charAt(i) != str.charAt(j)){
                j = next[j-1];
            }
            //当str.charAt(i) == str.charAt(j)满足时，部分匹配值就是+1
            if (str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }


    /**
     * 暴力匹配算法实现
     * @param str1 原字符串
     * @param str2 要匹配的字符串
     * @return 成功则返回下标，不成功则返回-1
     */
    public static int violenceSearch(String str1,String str2){
        //将字符串转换为char类型的数组
        char[] s1= str1.toCharArray();
        char[] s2 = str2.toCharArray();
        //分别得到字符串的长度
        int s1len = s1.length;
        int s2len =s2.length;
        int i =0;//i索引指向s1
        int j =0;//j索引指向s2
        while (i<s1len && j<s2len){//保证匹配时，不越界
            if (s1[i] == s2[j]){//说明匹配成功
                i++;
                j++;
            }else {//没有匹配成功
                //即str1[i]!=str2[j]),令i=i-(j-1),j=0
//                i = i -(j-1);
                i++;
                j =0;

            }
        }
        //判断是否匹配成功
        if (j==s2len){
            return i-j;
        }else {
            return -1;
        }
    }
}
