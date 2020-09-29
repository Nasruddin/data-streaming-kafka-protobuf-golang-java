package com.javatab.kafka.configuration;

import com.javatab.kafka.constants.KafkaConstants;
import com.javatab.kafka.model.proto.UserProtos;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializerConfig;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConfiguration<K,V> {

    public Consumer<K,V> configure() {
        Consumer<K,V> consumer = new KafkaConsumer<K, V>(getProperties());
        consumer.subscribe(Arrays.asList(KafkaConstants.TOPIC_USER_PROTOBUF));
        return consumer;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BOOTSTRAP_SERVERS);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.PROTOBUF_CONSUMER_GROUP);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaConstants.EARLIEST);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaProtobufDeserializer.class);

        properties.put(KafkaProtobufDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaConstants.SCHEMA_REGISTRY_URL);
        properties.put(KafkaProtobufDeserializerConfig.SPECIFIC_PROTOBUF_VALUE_TYPE, UserProtos.User.class.getName());
        return properties;
    }
}
