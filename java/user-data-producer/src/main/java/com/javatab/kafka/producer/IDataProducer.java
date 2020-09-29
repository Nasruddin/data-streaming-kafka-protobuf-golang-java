package com.javatab.kafka.producer;

public interface IDataProducer<K,V> {
    void publish(String topic, K key, V value);
}
