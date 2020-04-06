package com.autumn.hddfs.out;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OutFileDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置Jar包位置
        job.setJarByClass(OutFileDriver.class);
        //关联Mapper和Reducer类
        job.setMapperClass(OutFileMapper.class);
        job.setReducerClass(OutFileReducer.class);
        //设置Mapper输入的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        //设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setOutputFormatClass(OutFileOutputFormat.class);
        //设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
