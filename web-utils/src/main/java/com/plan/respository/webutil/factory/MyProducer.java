package com.plan.respository.webutil.factory;

import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

public class MyProducer{
    public static void main(String[] args) {
       /* //创建Kafka生产者的配置信息
        Properties properties = new Properties();
        //指定连接的Kafka集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"newyear:9092");
        //ACK应答级别
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,6);
        //批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        //等待时间
        properties.put(ProducerConfig.LINGER_MS_CONFIG,10);
        //RecordAccumulator缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        //key,value的序列化类
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");*/
        ArrayList<String> list = new ArrayList<>();
        //创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(getProperty("newyear:9092","all",3,16384,33554432,10,"com.plan.respository.webutil.factory.partitioner.OurPartition",list));
        //发送数据
        for (int i = 0; i < 10; i++) {
            //不带回调的生产者
//            producer.send(new ProducerRecord<>("first","producer---"+i));
            //带回调的生产者
            Future<RecordMetadata> second = producer.send(new ProducerRecord<>("second", "happynewyear" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.println(metadata.offset() + "========" + metadata.partition());
                    } else {
                        exception.printStackTrace();
                    }
                }
            });
            try {
                //同步发送的方式
                RecordMetadata recordMetadata = second.get();
            }catch (Exception e){
                e.printStackTrace();
            }

            //另外一种写法
            producer.send(new ProducerRecord<>("second", "happynewyear" + i),((metadata, exception) -> {
                if(exception == null){
                    System.out.println(metadata.partition()+"---"+metadata.offset());
                }else {
                    exception.printStackTrace();
                }
            }));
        }
        //关闭资源
        producer.close();


    }

    /**
     * 配置Kafka的信息
     * @param bootstrapServer 指定连接的Kafka集群
     * @param ack ACK应答级别
     * @param reties 重试次数
     * @param bachesize 批次大小
     * @param buffermemery RecordAccumulator缓冲区大小
     * @param lingerms 等待时间
     * @param partitioner 指定分区的全类名
     * @param list 拦截器的全类名的集合
     * @return 具体的配置信息
     */
    public static Properties getProperty(String bootstrapServer, String ack, int reties,
                                         int bachesize, int buffermemery,
                                         int lingerms, String partitioner,
                                         List<String> list){
        Properties properties = new Properties();
        //ACK应答级别
        properties.put(ProducerConfig.ACKS_CONFIG,ack);
        //指定连接的Kafka集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,reties);
        //批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,bachesize);
        //RecordAccumulator缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,buffermemery);
        //等待时间
        properties.put(ProducerConfig.LINGER_MS_CONFIG,lingerms);
        //key,value的序列化类
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //指定自定义分区
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,partitioner);
        //添加拦截器
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,list);
        return properties;
    }
}
