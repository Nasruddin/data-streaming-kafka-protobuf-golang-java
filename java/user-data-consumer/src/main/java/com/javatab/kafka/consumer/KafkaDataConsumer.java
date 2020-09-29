package com.javatab.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;


public class KafkaDataConsumer<K,V> implements IDataConsumer<K,V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDataConsumer.class);

    private final Consumer<K, V> consumer;

    public KafkaDataConsumer(Consumer<K, V> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void consume() {
        ConsumerRecords<K, V> records = consumer.poll(Duration.ofMillis(100));

        for (ConsumerRecord<K, V> record : records) {
            LOGGER.info("Record " + record.value());
        }
        consumer.commitAsync();
    }
}
