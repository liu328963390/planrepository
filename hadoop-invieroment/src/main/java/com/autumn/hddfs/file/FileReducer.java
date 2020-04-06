package com.autumn.hddfs.file;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FileReducer extends Reducer<Text, BytesWritable,Text,BytesWritable> {

    protected void reduce(Text key, Iterable<BytesWritable> values, Context context
    ) throws IOException, InterruptedException {

        //循环写出
        for (BytesWritable value : values) {
            context.write(key,value);
        }
    }
}
