package ru.vkr.model;

public enum TaskStatus {
    PREPARING(0, "Preparing"),
    IN_QUEUE(1, "In queue"),
    DOWNLOADING(2,"Downloading"),
    INSTALLING(3,"Installing");

    private final int code ;
    private final String status;
    TaskStatus(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public String getValue() {
        return status;
    }

    public static TaskStatus getByCode(int code) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.code == code) {
                return taskStatus;
            }
        }
        throw new IllegalArgumentException("Cant find status by code: " + code);
    }

    @Override
    public String toString() {
        return "TaskStatus{" +
                "code=" + code +
                ", status='" + status + '\'' +
                '}';
    }
}
