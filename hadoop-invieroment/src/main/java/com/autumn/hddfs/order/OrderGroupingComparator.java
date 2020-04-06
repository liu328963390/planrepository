package com.autumn.hddfs.order;

import com.autumn.hddfs.order.bean.OrderBean;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroupingComparator extends WritableComparator {

    public OrderGroupingComparator() {
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        //只要id相同，就认为是相同的key
        int result;
        OrderBean abean = (OrderBean) a;
        OrderBean bbean = (OrderBean) b;
        if (abean.getOrder_id()>bbean.getOrder_id()){
            result = 1;
        }else if (abean.getOrder_id()<bbean.getOrder_id()){
            result = -1;
        }else {
            result = 0;
        }
        return result;
    }
}
