package com.autumn.hddfs.sort.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SortBean implements  WritableComparable<SortBean> {

    private long upFlow;
    private long downFlow;
    private long sumFlow;

    /**
     * 无参构造
     */
    public SortBean() {
    }

    public SortBean(long upFlow, long downFlow) {
        super();
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        return  upFlow + "\t" + downFlow + "\t" + sumFlow ;
    }

    /**
     * 序列化方法
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    /**
     * 反序列化方法
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }

    public void set(long upFlow1,long downFlow1){
        upFlow = upFlow1;
        downFlow = downFlow1;
        sumFlow = upFlow1 + downFlow1;
    }

    /**
     * 比较方法
     * @param o
     * @return
     */
    @Override
    public int compareTo(SortBean o) {
        int result;
        //核心比较
        if (sumFlow>o.getSumFlow()){
            result = -1;
        }else if (sumFlow<o.getSumFlow()){
            result = 1;
        }else {
            result = 0;
        }
//        return this.sumFlow>o.getSumFlow()?-1:1;
        return result;
    }
}
