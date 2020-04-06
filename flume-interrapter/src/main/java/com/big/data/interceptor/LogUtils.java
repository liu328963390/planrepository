package com.big.data.interceptor;

import org.apache.commons.lang.math.NumberUtils;

public class LogUtils {

    public static boolean validateStart(String log) {
        if (log == null){
            return false;
        }
        if (!log.trim().startsWith("{") || !log.trim().endsWith("}")){
            return false;
        }
        return true;
    }

    public static boolean validateEvent(String log) {
        if (log == null){
            return false;
        }
        //时间|json
        //进行切割
        String[] split = log.split("\\|");
        //校验，长度是否是2
        if (split.length != 2){
            return false;
        }
        //先判断时间  NumberUtils.isDigits(split[0])判断是否全是数字  split[0].length() != 13判断时间长度是否是10
        if (split[0].length() != 13 || NumberUtils.isDigits(split[0])){
            return false;
        }
        //判断json
        if (!split[1].trim().startsWith("{") || !split[1].trim().endsWith("}")){
            return false;
        }
        return true;
    }
}
