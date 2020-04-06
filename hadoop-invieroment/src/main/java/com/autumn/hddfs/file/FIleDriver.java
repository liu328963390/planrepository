package com.autumn.hddfs.file;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

public class FIleDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"",""};
        //获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置输入的inputformat
        job.setInputFormatClass(FileFormat.class);
        //设置输出的outputformat
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        //设置分区数
        job.setNumReduceTasks(2);


        //配置Jar包存储位置
        job.setJarByClass(FIleDriver.class);
        //关联Mapper和Reducer类
        job.setMapperClass(FileMapper.class);
        job.setReducerClass(FileReducer.class);
        //配置Mapper的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
        //配置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);
        //设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);

    }
}
