package com.javatab.kafka;

import com.javatab.kafka.configuration.KafkaConfiguration;
import com.javatab.kafka.consumer.IDataConsumer;
import com.javatab.kafka.consumer.KafkaDataConsumer;
import com.javatab.kafka.model.proto.UserProtos;

public class Application {

    public static void main(String[] args) {
        Application.run();
    }

    private static void run() {
        KafkaConfiguration<String, UserProtos.User> kafkaConfiguration = new KafkaConfiguration<>();
        IDataConsumer<String, UserProtos.User> dataConsumer = new KafkaDataConsumer<>(kafkaConfiguration.configure());
        while (true) {
            dataConsumer.consume();
        }
    }
}
