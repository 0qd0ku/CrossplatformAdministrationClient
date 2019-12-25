package ru.vkr.model;

public enum OS {
    WINDOWS("Windows"),
    LINUX("Linux"),
    MACOS("MacOS");

    private String os;
    OS(String os) {
        this.os = os;
    }

    public String getValue() {
        return os;
    }

    @Override
    public String toString() {
        return "OS{" +
                "os='" + os + '\'' +
                '}';
    }
}
