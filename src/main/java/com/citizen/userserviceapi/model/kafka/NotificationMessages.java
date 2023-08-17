package com.citizen.userserviceapi.model.kafka;

import lombok.Getter;

@Getter
public enum NotificationMessages {

    CREATE_USER_MESSAGE("Created user"),
    UPDATE_USER_MESSAGE("Updated user"),
    DELETE_USER_MESSAGE("Deleted user");

    private final String message;

    NotificationMessages(String message) {
        this.message = message;
    }
}
