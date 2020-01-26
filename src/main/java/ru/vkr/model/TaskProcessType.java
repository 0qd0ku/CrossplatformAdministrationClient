package ru.vkr.model;

/**
 * Типы запускаемых файлов для запуска выполнения задачи
 */
public enum TaskProcessType {
    BAT("BAT"),
    POWERSHELL("POWERSHELL"),
    PROGRAM("PROGRAM"),
    SH("SH");

    private String type;
    TaskProcessType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }

    @Override
    public String toString() {
        return "TaskType{" +
                "type='" + type + '\'' +
                '}';
    }
}
