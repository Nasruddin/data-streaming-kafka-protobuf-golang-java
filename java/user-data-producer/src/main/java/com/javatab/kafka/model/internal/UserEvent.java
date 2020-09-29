package com.javatab.kafka.model.internal;

import com.javatab.kafka.model.proto.UserProtos;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEvent {
    private UserProtos.User internalUser;
    private UserProtos.User.Address internalAddress;
}
