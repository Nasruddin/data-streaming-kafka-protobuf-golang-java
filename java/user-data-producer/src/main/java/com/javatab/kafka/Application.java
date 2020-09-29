package com.javatab.kafka;

import com.github.javafaker.Faker;
import com.javatab.kafka.model.internal.UserEvent;
import com.javatab.kafka.model.proto.UserProtos;
import com.javatab.kafka.producer.IDataProducer;
import com.javatab.kafka.producer.KafkaDataProducer;
import com.javatab.kafka.configuration.KafkaConfiguration;
import com.javatab.kafka.utils.KafkaConstants;
import com.javatab.kafka.utils.UserEventGenerator;

import static java.lang.Thread.sleep;

public class Application {

    public static void main(String[] args) {
        Application.run();
    }

    private static void run() {
        KafkaConfiguration<String, UserProtos.User> kafkaConfiguration = new KafkaConfiguration<>();
        IDataProducer<String, UserProtos.User> dataProducer = new KafkaDataProducer<>(kafkaConfiguration.configure());
        UserEventGenerator userEventGenerator = new UserEventGenerator(new Faker());
        startSendingEvents(dataProducer, userEventGenerator);
    }

    private static void startSendingEvents(IDataProducer<String, UserProtos.User> dataProducer, UserEventGenerator userEventGenerator) {
        for (int i = 0; i < 10; i++) {
            UserEvent userEvent = userEventGenerator.generateUserEvents();
            UserProtos.User user = UserProtos.User
                    .newBuilder(userEvent.getInternalUser())
                    .setAddress(userEvent.getInternalAddress())
                    .build();
            dataProducer.publish(KafkaConstants.PROTO_USER_TOPIC, null, user);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
