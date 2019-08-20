package com.jumkid.oauthservice.model;

import java.util.Map;

public class User {

    public enum Fields {
        USERNAME("username", "username"), PASSWORD("password", "password"),
        EMAIL("email", "email"), ACTIVE("active", "active");

        private String value;
        private String columnName;
        private static Map<String, String> columnMap;

        Fields(String value, String columnName) { this.value = value; this.columnName = columnName; }

        public String value() { return this.value; }
        public String columnName() { return this.columnName; }

        public static String getColumnName(String value) {
            if(columnMap == null) {
                initColumnMap();
            }
            return columnMap.get(value);
        }

        private static void initColumnMap() {
            columnMap = Map.ofEntries(
                    Map.entry(USERNAME.value, USERNAME.columnName),
                    Map.entry(PASSWORD.value, PASSWORD.columnName),
                    Map.entry(EMAIL.value, EMAIL.columnName),
                    Map.entry(ACTIVE.value, ACTIVE.columnName)
            );
        }
    }

    private String username;
    private String password;
    private String email;
    private boolean active;

    private User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.active = builder.active;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public static class Builder {
        private String username;
        private String password;
        private String email;
        private boolean active;

        public User build() { return new User(this);}

        public Builder username(String val) { this.username = val; return this;}
        public Builder password(String val) { this.password = val; return this;}
        public Builder email(String val) { this.email = val; return this;}
        public Builder active(boolean val) { this.active = val; return this;}

    }

}
