package com.autumn.hddfs.divide;

import com.autumn.hddfs.divide.bean.DivideBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class DivideDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"E:\\123\\phone_data.txt","E:\\output2"};
        //获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置jar包位置
        job.setJarByClass(DivideDriver.class);
        //关联Mapper和Reducer类
        job.setMapperClass(DivideMapper.class);
        job.setReducerClass(DivideReducer.class);
        //设置Mapper的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DivideBean.class);
        //设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DivideBean.class);

        //设置分区
        job.setPartitionerClass(DividePartioner.class);
        //设置分区数,注意分区数与前面的分区相对应，不能多，多了，则多余的分区则为空的输出文件；若是小于分区数大于1，会报异常；若是1，则不管多少个分区，全部放到一个文件输出
        job.setNumReduceTasks(5);

        //设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
