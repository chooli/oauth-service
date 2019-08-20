package com.jumkid.oauthservice.model;

import java.util.Map;

public class Authority {

    public enum Fields {
        USERNAME("username", "username"), ROLE("role", "role");

        private String value;
        private String columnName;
        private static Map<String, String> columnMap;

        Fields(String value, String columnName){
            this.value = value;
            this.columnName = columnName;
        }

        public String value(){ return value; }
        public String columnName(){ return columnName; }

        public static String getColumnName(String value){
            if(columnMap == null) {
                initColumnMap();
            }
            return columnMap.get(value);
        }

        private static void initColumnMap(){
            columnMap = Map.ofEntries(
              Map.entry(USERNAME.value, USERNAME.columnName),
              Map.entry(ROLE.value, ROLE.columnName)
            );
        }

    }

    private String username;
    private String role;

    private Authority(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public static class Builder {
        private String username;
        private String role;

        public Authority build() { return new Authority(username, role); }

        public Builder username(String val) { this.username = val; return this; }
        public Builder role(String val) { this.role = val; return this; }
    }

}
