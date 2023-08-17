package com.citizen.userserviceapi.model.kafka;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Accessors(chain = true)
@Data
public class NotificationMessage {

    private String from;
    private String to;
    private String message;

}
