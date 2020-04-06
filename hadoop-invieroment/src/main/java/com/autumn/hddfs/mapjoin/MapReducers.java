package com.autumn.hddfs.mapjoin;

import com.autumn.hddfs.mapjoin.bean.MapBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MapReducers extends Reducer<Text, MapBean,MapBean, NullWritable> {

}
