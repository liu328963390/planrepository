package com.autumn.hddfs.join;

import com.autumn.hddfs.join.bean.JoinBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class JoinMapper extends Mapper<LongWritable, Text,Text, JoinBean> {

    String name;
    JoinBean joinBean = new JoinBean();
    Text k = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        name = fileSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        if (name.startsWith("order")){
            String[] split = line.split("\t");
            joinBean.setId(split[0]);
            joinBean.setPid(split[1]);
            joinBean.setAmount(Integer.parseInt(split[2]));
            joinBean.setPname("");
            joinBean.setFlag("order");
            k.set(split[1]);
        }else {
            String[] split = line.split("\t");
            joinBean.setId("");
            joinBean.setPid(split[0]);
            joinBean.setAmount(0);
            joinBean.setPname(split[1]);
            joinBean.setFlag("pc");
            k.set(split[0]);

        }
        context.write(k,joinBean);
    }
}
