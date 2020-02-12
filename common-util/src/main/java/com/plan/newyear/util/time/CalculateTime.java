package com.plan.newyear.util.time;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * 计算日期时间工具类
 */
public class CalculateTime {

    /**
     * 获取本年开始时间
     * @return
     */
    public static Date getYearStart(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date time=cal.getTime();
        return time;
    }

    /**
     * 时间相减得到这段时间内的所有日期的集合（开始时间和结束时间可任意大小）
     * @param beginDateStr（格式为"yyyy-MM-dd"，也可为"yyyy-MM-dd hh:mm:ss"）
     * @param endDateStr
     * @return  long
     */
    public static List<String> getDaySub(String beginDateStr, String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (beginDateStr!=null) {
            Date beginDate = null;
            Date endDate = null;
            try {
                beginDate = format.parse(beginDateStr);
                endDate = format.parse(endDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        }else {
            Date endDate = null;
            try {
                endDate = format.parse(endDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            day=(endDate.getTime())/(24*60*60*1000);
        }
        List<String> list = new ArrayList();
        for (int i = 0; i < day; i++) {
            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.DAY_OF_YEAR,-i);
            String format1 = format.format(ca.getTime());
            list.add(format1);
        }
        Arrays.asList(list);
        return list;
    }
}