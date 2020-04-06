package com.autumn.hddfs.order.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {

    private int order_id;//订单id
    private double price;//价格

    public OrderBean() {
    }

    public OrderBean(int order_id, double price) {
        this.order_id = order_id;
        this.price = price;
    }

    @Override
    public int compareTo(OrderBean o) {
        //先按订单进行升序排序，若相同，按价格降序
        int result;
        if (order_id>o.getOrder_id()){
            result =1;
        }else if (order_id<o.getOrder_id()){
            result = -1;
        }else {
            if (price>o.getPrice()){
                result = -1;
            }else if (price<o.getPrice()){
                result = 1;
            }else {
                result = 0;
            }
        }
        return result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(order_id);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        order_id = in.readInt();
        price = in.readDouble();
    }

    @Override
    public String toString() {
        return order_id + "\t "+ price;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
