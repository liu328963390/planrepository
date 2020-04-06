package com.autumn.hddfs.flow;

import com.autumn.hddfs.flow.bean.FlowBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text,Text, FlowBean> {
    Text k = new Text();
    FlowBean v = new FlowBean();
    @Override
    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //切割 \t
        String[] field = line.split("\t");
        //封装对象
        k.set(field[1]);
        long upFlow = Long.parseLong(field[field.length-3]);
        long downFlow = Long.parseLong(field[field.length-2]);
        v.setUpFlow(upFlow);
        v.setDownFlow(downFlow);
//        v.set(upFlow,downFlow);
        //写出
        context.write(k,v);
    }
}
