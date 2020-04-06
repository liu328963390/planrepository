package com.autumn.hddfs.frontword;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FrontMapper extends Mapper<Text,Text,Text, IntWritable> {
    IntWritable intWritable = new IntWritable();
    @Override
    protected void map(Text key, Text value,
                       Context context) throws IOException, InterruptedException {
        //封装对象

        //写出数据
        context.write(key,intWritable);
    }
}
