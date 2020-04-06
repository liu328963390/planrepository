package com.autumn.hddfs.index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TwoIndexReduce extends Reducer<Text,Text,Text,Text> {

    Text v = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //拼接字符串
        StringBuffer sb = new StringBuffer();
        for (Text value : values) {
            sb.append(value.toString().replace("\t","-->")+"\t");
        }
        v.set(sb.toString());
        //写出
        context.write(key,v);
    }
}
