package com.autumn.hddfs.index;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OneIndexDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"E:/inputs","E:/ouinputs1"};
        //获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //配置Jar包输出位置
        job.setJarByClass(OneIndexDriver.class);
        //关联Mapper和Reducer类
        job.setMapperClass(OneIndexMapper.class);
        job.setReducerClass(OndeIndexReduce.class);
        //设置Mapper的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置输入输出的路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}
