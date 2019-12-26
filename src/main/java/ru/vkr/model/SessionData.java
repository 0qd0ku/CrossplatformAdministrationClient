package ru.vkr.model;

public class SessionData {
    public enum SessionType {
        ADMIN(1, "admin"), CLIENT(2, "client");

        private final int code;
        private final String description;

        SessionType(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        public static SessionType getTypeByCode(int code) {
            for (SessionType type : SessionType.values()) {
                if (type.code == code) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Not found session type by code: " + code);
        }


        @Override
        public String toString() {
            return "SessionType{" +
                    "code=" + code +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
    private String token;
    private SessionType sessionType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    @Override
    public String toString() {
        return "SessionDataDto{" +
                "token='" + token + '\'' +
                ", sessionType=" + sessionType +
                '}';
    }
}
