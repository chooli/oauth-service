package com.jumkid.oauthservice.dao;

import com.jumkid.oauthservice.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User.Builder()
                .username(rs.getString(User.Fields.USERNAME.columnName()))
                .email(rs.getString(User.Fields.EMAIL.columnName()))
                .active(rs.getBoolean(User.Fields.ACTIVE.columnName()))
                .build();
    }

}
