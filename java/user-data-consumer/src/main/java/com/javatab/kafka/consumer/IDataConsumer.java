package com.javatab.kafka.consumer;

import com.javatab.kafka.model.proto.UserProtos;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.List;

public interface IDataConsumer<K,V> {
    void consume();
}
