package ru.vkr.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Статусы задачи
 */
public enum TaskStatus {
    PREPARING("Preparing"),
    IN_QUEUE("In queue"),
    DOWNLOADING("Downloading"),
    ERROR_INSTALLING("Error installing"),
    INSTALLING("Installing");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static TaskStatus getByName(String name) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.status.equals(name)) {
                return taskStatus;
            }
        }
        throw new IllegalArgumentException("Cant find status by name: " + name);
    }

    @Override
    public String toString() {
        return "TaskStatus{" +
                ", status='" + status + '\'' +
                '}';
    }
}
