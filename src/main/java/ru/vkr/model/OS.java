package ru.vkr.model;

/**
 * Типы операционных систем
 */
public enum OS {
    WINDOWS("Windows"),
    LINUX("Linux"),
    MACOS("MacOS");

    private final String name;
    OS(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OS getOsByName(String osName) {
        for (OS os : OS.values()) {
            if (osName.contains(os.name)) {
                return os;
            }
        }
        throw new IllegalArgumentException("Cant find os type aby name");
    }

    @Override
    public String toString() {
        return "OS{" +
                "name='" + name + '\'' +
                '}';
    }
}
