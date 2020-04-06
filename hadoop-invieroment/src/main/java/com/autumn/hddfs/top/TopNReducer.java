package com.autumn.hddfs.top;

import com.autumn.hddfs.top.bean.TopBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

public class TopNReducer extends Reducer<TopBean, Text,Text,TopBean> {

    private TreeMap<TopBean,Text> flowmap = new TreeMap<>();

    @Override
    protected void reduce(TopBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            TopBean topBean = new TopBean();
            topBean.set(key.getUpFlow(),key.getDownFlow());
            //向flowmap集合中添加数据
            flowmap.put(topBean,new Text(value));
            //限制flowmap数据量，超过限制量就删除掉流量最小的一条数据
            if (flowmap.size()>10){
                flowmap.remove(flowmap.lastKey());
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //遍历集合，输出数据
        Iterator<TopBean> iterator = flowmap.keySet().iterator();
        while (iterator.hasNext()){
            TopBean next = iterator.next();
            context.write(new Text(flowmap.get(next)),next);
        }
    }
}
