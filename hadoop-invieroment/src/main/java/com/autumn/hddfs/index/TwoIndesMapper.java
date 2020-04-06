package com.autumn.hddfs.index;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TwoIndesMapper extends Mapper<LongWritable, Text,Text,Text> {

    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行,并切割
        String[] split = value.toString().split("--");
        //封装
        k.set(split[0]);
        v.set(split[1]);
        //写出
        context.write(k,v);
    }
}
