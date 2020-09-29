package com.javatab.kafka.constants;

public class KafkaConstants {

    public static final String TOPIC_USER_PROTOBUF = "user-protobuf";

    private KafkaConstants(){}

    public static final String EARLIEST = "earliest";
    public static final String SCHEMA_REGISTRY_URL = "http://localhost:8081";
    public static final String BOOTSTRAP_SERVERS = "http://localhost:9092";
    public static final String PROTOBUF_CONSUMER_GROUP = "protobuf-consumer-group";
}
