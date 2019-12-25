package ru.vkr.model;

public enum TaskStatus {
    PREPARING("Preparing"),
    IN_QUEUE("In queue"),
    DOWNLOADING("Downloading"),
    INSTALLING("Installing");

    private String status;
    TaskStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }

    @Override
    public String toString() {
        return "TaskStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
