package com.autumn.hddfs.join;

import com.autumn.hddfs.join.bean.JoinBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class JoinReducer extends Reducer<Text, JoinBean,JoinBean, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<JoinBean> values, Context context) throws IOException, InterruptedException {
        //存放所有订单集合
        ArrayList<JoinBean> list = new ArrayList<>();
        //存放产品信息
        JoinBean pBean = new JoinBean();

        for (JoinBean value : values) {
            if ("order".equals(value.getFlag())){
                JoinBean joinBean = new JoinBean();
                try {
                    BeanUtils.copyProperties(joinBean,value);
                    list.add(joinBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    BeanUtils.copyProperties(pBean,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        for (JoinBean joinBean : list) {
            joinBean.setPname(pBean.getPname());
            context.write(joinBean,NullWritable.get());
        }
    }
}
