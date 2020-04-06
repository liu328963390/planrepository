package com.autumn.hddfs.nline;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class LineDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"",""};//输入输出路径
        //获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //设置每个切片InputSplit中划分三条记录
        NLineInputFormat.setNumLinesPerSplit(job,3);
        //使用NLineInputFormat处理记录数
        job.setInputFormatClass(NLineInputFormat.class);


        //设置jar包位置
        job.setJarByClass(LineDriver.class);
        //关联Mapper和Reducer类
        job.setMapperClass(LineMapper.class);
        job.setReducerClass(LineReducer.class);
        //设置mapper的key和value的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
