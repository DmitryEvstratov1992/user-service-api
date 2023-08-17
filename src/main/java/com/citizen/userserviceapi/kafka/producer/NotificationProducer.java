package com.citizen.userserviceapi.kafka.producer;

import com.citizen.userserviceapi.model.kafka.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducer {

    @Value("${spring.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    public void sendMessage(NotificationMessage message) {
        log.info("Producing message -> {} to topic {}", message, topic);

        this.kafkaTemplate.send(topic, message);
    }

}
