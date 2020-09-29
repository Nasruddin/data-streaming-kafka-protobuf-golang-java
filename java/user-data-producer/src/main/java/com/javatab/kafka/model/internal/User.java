package com.javatab.kafka.model.internal;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class User {
    private String name;
    private String id;
    private String email;
    private Instant created_on;
}
