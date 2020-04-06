package com.autumn.hddfs.divide;

import com.autumn.hddfs.divide.bean.DivideBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DivideMapper extends Mapper<LongWritable,Text,Text,DivideBean> {

    Text k = new Text();
    DivideBean v = new DivideBean();
    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {
        //获取一行的数据
        String line = value.toString();
        //切割
        String[] split = line.split("\t");
        //封装
        k.set(split[1]);
        long upflow = Long.parseLong(split[split.length-3]);
        long downflow = Long.parseLong(split[split.length-2]);
        v.setUpFlow(upflow);
        v.setDownFlow(downflow);
        v.set(upflow,downflow);
        //写出
        context.write(k,v);
    }

}
