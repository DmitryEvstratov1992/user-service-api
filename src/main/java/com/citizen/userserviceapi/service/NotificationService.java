package com.citizen.userserviceapi.service;

import com.citizen.userserviceapi.model.kafka.NotificationMessage;

public interface NotificationService {

    void sendMessage(NotificationMessage message);

}
