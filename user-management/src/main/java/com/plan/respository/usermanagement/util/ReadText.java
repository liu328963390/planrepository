package com.plan.respository.usermanagement.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.HashMap;

public class ReadText {

    public ReadText() {
    }

    public static String readtxt(String path) throws Exception{
        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer();
        String temp = null;
        while ((temp = bufferedReader.readLine()) != null){
            buffer.append(temp+"\r\n");
        }
        bufferedReader.close();
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception{
        String readtxt = readtxt("E:\\word.txt");
        System.out.println(readtxt);
        String[] split = readtxt.split("\n");
        HashMap<Integer, String> map = new HashMap<>();

        int count = 0;
        for (int i = 0; i < split.length; i++) {
//            map.put()
        }
        System.out.println(count);
    }
}
