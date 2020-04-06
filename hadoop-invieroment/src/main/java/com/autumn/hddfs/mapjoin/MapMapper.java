package com.autumn.hddfs.mapjoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

public class MapMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    HashMap<String, String> map = new HashMap<>();
    Text k = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath().toString();
        //缓存小表
        BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
        String line;
        while (StringUtils.isNotEmpty(line = read.readLine())){
            //切割
            String[] split = line.split("\t");
            map.put(split[0],split[1]);
        }
        //关闭资源
        IOUtils.closeStream(read);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行的数据
        String line = value.toString();
        //切割
        String[] split = line.split("\t");
        //获取pid
        String pid = split[1];
        //取出pname
        String pname = map.get(pid);
        //拼接
        line = line +"\t"+ pname;
        //写出
        k.set(line);
        context.write(k,NullWritable.get());
    }
}
