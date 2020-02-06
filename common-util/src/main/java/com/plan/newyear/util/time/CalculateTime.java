package com.plan.newyear.util.time;

import java.util.Calendar;
import java.util.Date;

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
}