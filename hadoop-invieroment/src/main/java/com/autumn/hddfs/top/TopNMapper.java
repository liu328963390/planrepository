package com.autumn.hddfs.top;

import com.autumn.hddfs.top.bean.TopBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

public class TopNMapper extends Mapper<LongWritable, Text, TopBean,Text> {

    //定义一个treemap作为存储数据的容器
    private TreeMap<TopBean,Text> flowMap = new TreeMap<>();
    private  TopBean topBean;
    private Text v;

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //遍历flowMap集合，输出数据
        Iterator<TopBean> iterator = flowMap.keySet().iterator();
        while (iterator.hasNext()){
            TopBean next = iterator.next();
            context.write(next,flowMap.get(next));
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        topBean = new TopBean();
        v = new Text();
        //获取一行,切割
        String[] split = value.toString().split("\t");
        //封装数据
        String phoneNum = split[0];
        long upflow = Long.parseLong(split[1]);
        long downflow = Long.parseLong(split[2]);
        long sumflow = Long.parseLong(split[3]);

        topBean.setUpFlow(upflow);
        topBean.setDownFlow(downflow);
        topBean.setSumFlow(sumflow);
        v.set(phoneNum);
        //向flowMap中添加数据
        flowMap.put(topBean,v);
        //限制flowMap的数据量，超过限定就删除掉流量最小的
        if (flowMap.size()>10){
            flowMap.remove(flowMap.lastKey());
        }
    }
}
