package com.plan.respository.webutil.factory.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * 消费者
 */
public class MyConsumer {
    public static void main(String[] args) {
        //创建消费者
        KafkaConsumer<String, String> first = new KafkaConsumer<>(getConsumerProperties("", 1000, false, "first","earliest"));
        //订阅主题
        first.subscribe(Arrays.asList("fist","second"), new ConsumerRebalanceListener() {
           //该方法会在RebaLance之前调用
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
//                commitoffset(currentoffset);
            }

            //之后调用
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
//                currentoffset.clear();
                for (TopicPartition partition : partitions) {
                    //定位到最近提交的offset位置继续消费
                    first.seek(partition,getoffset(partition));
                }
            }
        });
        //阻塞进程，使其不让关闭
        while (true) {
            //获取数据
            ConsumerRecords<String, String> poll = first.poll(100);
            //解析并打印ConsumerRecords
            for (ConsumerRecord<String, String> consumerRecord : poll) {
                System.out.println(consumerRecord.key() + "----" + consumerRecord.value());
//                currentoffset.put(new TopicPartition(consumerRecord.topic(),consumerRecord.partition(),consumerRecord.offset()));
            }
            //异步提交
//            commitoffset(cuurentoffset);
        }


    }

    public static long getoffset(TopicPartition partition){
        //从mysql中读数据
        return 0;
    }

    private static void commitoffset(Map<TopicPartition,Long> currentoffset){
//mysql提交到
    }

    /**
     * 消费者配置信息
     * @param bootstrapServer 指定的Kafka集群
     * @param autoCommitMs 自动提交的时间
     * @param enableAutoCommit 是否开启自动提交
     * @param gourpId 消费者组
     * @param offset 重置消费者的offset模式 默认是latest最新的,修改为earliest最早的，none
     * @return
     */
    private static Properties getConsumerProperties(String bootstrapServer,int autoCommitMs
          ,boolean enableAutoCommit,String gourpId,String offset){
        Properties properties = new Properties();
        //连接集群
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        //自动提交延时
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,autoCommitMs);
        //开启自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,enableAutoCommit);
        //Key,value的反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        //消费者组名
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,gourpId);
        //重置消费者的offset
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,offset);
        return properties;
    }
}
