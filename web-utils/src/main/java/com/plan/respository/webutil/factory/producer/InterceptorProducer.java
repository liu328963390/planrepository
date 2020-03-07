package com.plan.respository.webutil.factory.producer;

import com.plan.respository.webutil.factory.MyProducer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.ArrayList;
import java.util.List;

public class InterceptorProducer {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("com.plan.respository.webutil.factory.interceptor.TimeIntercepto");
        list.add("com.plan.respository.webutil.factory.interceptor.CounterInterceptor");
        new KafkaProducer<String,String>(MyProducer.getProperty("","",3,23,23,23,"",list));
    }
}
