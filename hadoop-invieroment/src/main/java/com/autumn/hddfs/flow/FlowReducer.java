package com.autumn.hddfs.flow;

import com.autumn.hddfs.flow.bean.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text, FlowBean,Text,FlowBean> {
    FlowBean v = new FlowBean();

    protected void reduce(Text key, Iterable<FlowBean> values, Context context
    ) throws IOException, InterruptedException {
        long sum_upFLow = 0;
        long sum_downFlow = 0;
        //累加求和
        for (FlowBean value : values) {
            sum_upFLow += value.getUpFlow();
            sum_downFlow += value.getDownFlow();
        }
        v.set(sum_upFLow,sum_downFlow);
        //写出
        context.write(key,v);
    }
}
