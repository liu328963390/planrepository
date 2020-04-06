package com.autumn.hddfs.clear;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //解析数据
        boolean result = parse(line,context);
        if (!result){
            return;
        }
        //解析通过写出
        context.write(value,NullWritable.get());
    }

    private boolean parse(String line, Context context) {
        //切割
        String[] split = line.split(" ");
        if (split.length >11){
            context.getCounter("map","true").increment(1);
            return  true;
        }else {
            context.getCounter("map","false").increment(1);
            return false;
        }
    }
}
