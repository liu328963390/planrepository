package com.autumn.hddfs.nline;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LineMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    Text text = new Text();
    IntWritable intWritable = new IntWritable();
    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {
        //获取一行
        String ling = value.toString();
        //切割
        String[] split = ling.split(" ");
        for (String word : split) {
            text.set(word);
            context.write(text,intWritable);
        }
    }
}
