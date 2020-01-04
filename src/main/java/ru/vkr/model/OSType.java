package ru.vkr.model;

public enum OSType {
    X86("x86"),
    X64("x64");

    private final String name;

    OSType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OSType spotOSType(String name) {
        String X_32_ARCH = "32";
        String X_64_ARCH = "64";
        String X_86_ARCH = "86";
        if (name.contains(X_32_ARCH) || name.contains(X_86_ARCH)) {
            return X86;
        } else if (name.contains(X_64_ARCH)) {
            return X64;
        }
        throw new IllegalArgumentException("Cant spot type by name: " + name);
    }

    @Override
    public String toString() {
        return "OSType{" +
                "name='" + name + '\'' +
                '}';
    }
}
