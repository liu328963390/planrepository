package com.autumn.hddfs.join;

import com.autumn.hddfs.join.bean.JoinBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class JoinDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"E:\\input","E:\\output0"};
        //获取Job对象
        Configuration entries = new Configuration();
        Job job = Job.getInstance(entries);
        //设置Jar包位置
        job.setJarByClass(JoinDriver.class);
        //关联Mapper和Reducer类
        job.setMapperClass(JoinMapper.class);
        job.setReducerClass(JoinReducer.class);
        //设置Mapper的Key和Value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(JoinBean.class);
        //设置最终输出的key和value类型
        job.setOutputKeyClass(JoinBean.class);
        job.setOutputValueClass(NullWritable.class);
        //设置输入输出的路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
