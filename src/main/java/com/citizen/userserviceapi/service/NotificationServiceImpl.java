package com.citizen.userserviceapi.service;

import com.citizen.userserviceapi.kafka.producer.NotificationProducer;
import com.citizen.userserviceapi.model.kafka.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationProducer notificationProducer;

    @Async("threadPoolTaskExecutor")
    @Override
    public void sendMessage(NotificationMessage message) {
        log.info("Send message to kafka");
        notificationProducer.sendMessage(message);
    }

}
