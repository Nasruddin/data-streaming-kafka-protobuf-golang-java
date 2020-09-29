package com.javatab.kafka.model.internal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String streetName;
    private String city;
    private String state;
    private String zipCode;
}
