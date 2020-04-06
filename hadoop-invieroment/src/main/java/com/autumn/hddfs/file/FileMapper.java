package com.autumn.hddfs.file;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FileMapper extends Mapper<Text, BytesWritable,Text,BytesWritable> {

    protected void map(Text key, BytesWritable value,
                       Context context) throws IOException, InterruptedException {
        context.write(key,value);
    }
}
