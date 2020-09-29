package com.javatab.kafka.utils;

import com.github.javafaker.Faker;
import com.google.protobuf.Timestamp;
import com.javatab.kafka.model.internal.UserEvent;
import com.javatab.kafka.model.proto.UserProtos;

import java.time.Instant;
import java.util.UUID;

public class UserEventGenerator {

    private final Faker faker;

    public UserEventGenerator(Faker faker) {
        this.faker = faker;
    }

    public UserEvent generateUserEvents() {
        return UserEvent.builder()
                .internalUser(generateUser())
                .internalAddress(generateAddress())
                .build();
    }

    private UserProtos.User.Address generateAddress() {
        return UserProtos.User.Address.newBuilder()
                .setStreetName(faker.address().streetName())
                .setCity(faker.address().city())
                .setState(faker.address().state())
                .setZipCode(faker.address().zipCode())
                .build();
    }

    private UserProtos.User generateUser() {
        return UserProtos.User.newBuilder()
                .setId(generateId().toString())
                .setEmail(generateEmail().toString())
                .setCreatedOn(generateTimestamp())
                .build();
    }

    private UUID generateId() {
        return UUID.randomUUID();
    }

    private StringBuilder generateEmail() {
        return new StringBuilder(faker.name().firstName() + "_" + faker.name().lastName() + "@email.com");
    }

    private Timestamp generateTimestamp() {
        Instant time = Instant.now();
        return Timestamp.newBuilder().setSeconds(time.getEpochSecond())
                .setNanos(time.getNano()).build();
    }
}
