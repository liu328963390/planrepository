package com.autumn.hddfs.words.counts;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * map阶段
 * KEYIN 输入数据的key
 * VALUEIN 输入数据的value
 * KEYOUT 输出的数据key类型
 * VALUEOUT 输出的数据的value类型
 */
public class WordMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    Text text = new Text();
    IntWritable writable = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //切割单词
        String[] split = line.split(" ");
        //循环写出
        for (String word : split) {
            text.set(word);
            context.write(text,writable);
        }
    }
}
