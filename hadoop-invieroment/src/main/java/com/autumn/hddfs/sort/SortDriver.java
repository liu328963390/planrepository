package com.autumn.hddfs.sort;

import com.autumn.hddfs.sort.bean.SortBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class SortDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"E:\\output","E:\\output5"};
        //获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置Jar包存储位置
        job.setJarByClass(SortDriver.class);
        //关联Mapper和Reducer类
        job.setMapperClass(SortMapper.class);
        job.setReducerClass(SortReducer.class);
        //设置Mapper的key和value类型
        job.setMapOutputKeyClass(SortBean.class);
        job.setMapOutputValueClass(Text.class);
        //设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(SortBean.class);

        //关联分区
        job.setPartitionerClass(SortPartitioner.class);
        job.setNumReduceTasks(6);

        //设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?1:0);
    }
}
