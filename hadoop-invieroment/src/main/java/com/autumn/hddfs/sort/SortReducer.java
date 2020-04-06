package com.autumn.hddfs.sort;

import com.autumn.hddfs.sort.bean.SortBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SortReducer extends Reducer<SortBean, Text,Text,SortBean> {

    protected void reduce(SortBean key, Iterable<Text> values, Context context
    ) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value,key);
        }
    }
}
