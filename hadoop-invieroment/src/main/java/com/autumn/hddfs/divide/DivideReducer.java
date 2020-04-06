package com.autumn.hddfs.divide;

import com.autumn.hddfs.divide.bean.DivideBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DivideReducer extends Reducer<Text, DivideBean,Text,DivideBean> {
    DivideBean v = new DivideBean();

    protected void reduce(Text key, Iterable<DivideBean> values, Context context
    ) throws IOException, InterruptedException {
        long sum_upflow = 0;
        long sum_downflow = 0;

        //进行累加求和
        for (DivideBean value : values) {
            sum_upflow += value.getUpFlow();
            sum_downflow += value.getDownFlow();
        }
        v.set(sum_upflow,sum_downflow);
        //写出
        context.write(key,v);
    }
}
