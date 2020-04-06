package com.autumn.hddfs.top.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TopBean implements WritableComparable<TopBean> {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public TopBean() {
        super();
    }

    public TopBean(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    @Override
    public int compareTo(TopBean o) {
        int result ;
        if (this.sumFlow>o.getSumFlow()){
            result = -1;
        }else if (this.sumFlow<o.getSumFlow()){
            result = 1;
        }else {
            result = 0;
        }
        return result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }

    public void set(long upflow,long downflow){
        upFlow = upflow;
        downFlow = downflow;
        sumFlow = upflow+downflow;
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow ;
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
}
