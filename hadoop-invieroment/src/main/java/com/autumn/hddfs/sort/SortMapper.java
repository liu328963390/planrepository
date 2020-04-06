package com.autumn.hddfs.sort;

import com.autumn.hddfs.sort.bean.SortBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortMapper extends Mapper<LongWritable, Text, SortBean,Text> {

    SortBean k = new SortBean();
    Text v = new Text();

    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //切割
        String[] split = line.split("\t");
        //封装对象
        String phone = split[0];
        long upFlow = Long.parseLong(split[1]);
        long downFlow = Long.parseLong(split[2]);
        long sumFlow = Long.parseLong(split[3]);
        k.setUpFlow(upFlow);
        k.setDownFlow(downFlow);
        k.setSumFlow(sumFlow);
        v.set(phone);
        //写出
        context.write(k,v);
    }

}
