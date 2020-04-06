package com.autumn.hddfs.words.counts;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Driver类
 */
public class WordDriver {

    public static void main(String[] args) throws IOException,ClassNotFoundException,InterruptedException {
        args = new String[]{"D:\\logs\\info.log","D:\\output3"};
        Configuration conf = new Configuration();

        //开启map端输出压缩
        conf.setBoolean("mapreduce.map.output.compress",true);
        //设置map端输出压缩方式
        conf.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);


        //获取Job对象
        Job job = Job.getInstance(conf);
        //设置jar存储位置
        job.setJarByClass(WordDriver.class);
        //关联Map和Reducer类
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);
        //设置Mapper阶段输出数据的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置最终数据输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
         //设置分区数
        job.setNumReduceTasks(2);


        //将小文件都归并到一个切片上面
        //如果不设置InputFormat，它默认用的是TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);
        //虚拟存储切片最大值设置4m（小文件的总和为最佳）
        CombineTextInputFormat.setMaxInputSplitSize(job,4194304);

        //设置reduce端输出压缩开启
        FileOutputFormat.setCompressOutput(job,true);
        //设置压缩的方式
//        FileOutputFormat.setOutputCompressorClass(job,BZip2Codec.class);
        FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);

        //设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交Job
        job.submit();
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
