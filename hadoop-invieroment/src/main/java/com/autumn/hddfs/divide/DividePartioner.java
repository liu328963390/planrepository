package com.autumn.hddfs.divide;

import com.autumn.hddfs.divide.bean.DivideBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class DividePartioner extends Partitioner<Text, DivideBean> {
    @Override
    public int getPartition(Text text, DivideBean divideBean, int numPartitions) {
        //key是手机号
        //value是流量信息
        //获取手机号前3位
        String prePhone = text.toString().substring(0, 3);
        //进行判断
        int partition = 4;
        if ("136".equals(prePhone)){
            partition = 0;
        }else if ("137".equals(prePhone)){
            partition = 1;
        }else if ("138".equals(prePhone)){
            partition = 2;
        }else if ("139".equals(prePhone)){
            partition =3;
        }
        return partition;
    }
}
