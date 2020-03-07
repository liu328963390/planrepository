package com.plan.respository.webutil.factory.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class TimeIntercepto implements ProducerInterceptor<String,String> {
    private int success;
    private int errors;
    @Override
    public void configure(Map<String, ?> configs) {

    }

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        //取出具体数据
        String value = record.value();
        String values = System.currentTimeMillis()+value;
        //创建一个新的ProducerRecord对象，并返回
        ProducerRecord<String, String> newrecord = new ProducerRecord<>(record.topic(), record.partition(), record.key(), values);
        return newrecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (metadata!=null){
            success++;
        }else {
            errors++;
        }
    }

    @Override
    public void close() {
        System.out.println("success="+success+",errors="+errors);
    }
}
