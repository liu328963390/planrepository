package com.autumn.hddfs.sort;

import com.autumn.hddfs.sort.bean.SortBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class SortPartitioner extends Partitioner<SortBean, Text> {
    @Override
    public int getPartition(SortBean sortBean, Text text, int numPartitions) {
        //按手机号前三位进行分区
        String prePhone = text.toString().substring(0, 3);
        int partition = 5;
        if ("135".equals(prePhone)){
            partition = 0;
        }else if ("136".equals(prePhone)){
            partition = 1;
        }else if ("137".equals(prePhone)){
            partition = 2;
        }else if ("138".equals(prePhone)){
            partition = 3;
        }else if ("139".equals(prePhone)){
            partition = 4;
        }
        return partition;
    }
}
