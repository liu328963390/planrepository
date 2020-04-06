package com.autumn.hddfs.order;

import com.autumn.hddfs.order.bean.OrderBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    OrderBean k = new OrderBean();


    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {
        //获取一行数据,并进行切割
        String[] split = value.toString().split("\t");
        //封装对象
        k.setOrder_id(Integer.parseInt(split[0]));
        k.setPrice(Double.parseDouble(split[2]));
        //写出数据
        context.write(k,NullWritable.get());
    }
}
