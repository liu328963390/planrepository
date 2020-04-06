package com.autumn.hddfs.nline;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LineReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    IntWritable v =new IntWritable();
    protected void reduce(Text key, Iterable<IntWritable> values, Context context
    ) throws IOException, InterruptedException {
        //累加求和
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        v.set(sum);
        //写出
        context.write(key,v);
    }
}
