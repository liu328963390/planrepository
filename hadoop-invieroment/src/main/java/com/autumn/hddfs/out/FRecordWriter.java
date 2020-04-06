package com.autumn.hddfs.out;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class FRecordWriter extends RecordWriter<Text, NullWritable> {

    FSDataOutputStream fosOne;
    FSDataOutputStream fostwo;

    public FRecordWriter(TaskAttemptContext job) {
        //获取文件系统
        try {
            FileSystem fs = FileSystem.get(job.getConfiguration());
            //创建输出到第一个输出表
             fosOne = fs.create(new Path(""));
            //创建输出到另一个输出表
             fostwo = fs.create(new Path(""));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        //判断key中是否有第一个文件要的内容，若有则写到第一个文件，没有则写到另一个文件
        if (key.toString().contains("baidu")){
            fosOne.write(key.toString().getBytes());
        }else {
            fostwo.write(key.toString().getBytes());
        }
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(fosOne);
        IOUtils.closeStream(fostwo);
    }
}
