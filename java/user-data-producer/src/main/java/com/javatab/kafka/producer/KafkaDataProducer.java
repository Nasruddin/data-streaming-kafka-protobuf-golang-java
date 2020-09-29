package com.javatab.kafka.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaDataProducer<K,V> implements IDataProducer<K,V> {

    private final Producer<K, V> producer;

    public KafkaDataProducer(Producer<K, V> producer) {
        this.producer = producer;
    }

    public void publish(String topic, K key, V value) {
        ProducerRecord<K, V> record
                = new ProducerRecord<>(topic, key,
                value);
        producer.send(record);
    }
}
