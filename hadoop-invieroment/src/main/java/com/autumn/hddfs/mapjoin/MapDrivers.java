package com.autumn.hddfs.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MapDrivers {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        args = new String[]{"E:\\input","E:\\output3"};
        //获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置Jar包存放位置
        job.setJarByClass(MapDrivers.class);
        //关联Mapper和Reducer类
        job.setMapperClass(MapMapper.class);
//        job.setReducerClass(MapReducers.class);
        //设置Mapper的key和Value类型
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(MapBean.class);

        //设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //加载缓存数据
        job.addCacheFile(new URI("file:///E:/cache/pd.txt"));
        job.setNumReduceTasks(0);

        //设置输入输出的路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);

    }
}
