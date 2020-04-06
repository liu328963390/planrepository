package com.autumn.hddfs.frontword;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FrontReduce extends Reducer<Text, IntWritable,Text,IntWritable> {

    IntWritable intWritable = new IntWritable();
    protected void reduce(Text key, Iterable<IntWritable> values, Context context
    ) throws IOException, InterruptedException {
        int sum = 0;
       //累加求和
        for (IntWritable value : values) {
            sum += value.get();
        }
        intWritable.set(sum);
        //写出
        context.write(key,intWritable);

    }
}
