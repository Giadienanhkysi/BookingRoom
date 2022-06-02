package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.User;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet resultSet) {
		try {
            User user = new User();
            user.setId(resultSet.getInt("ID"));
            user.setUsername(resultSet.getString("USERNAME"));
            user.setFirst_name(resultSet.getString("FIRST_NAME"));
            user.setLast_name(resultSet.getString("LAST_NAME"));
            user.setGender(resultSet.getString("GENDER"));
            user.setPhone(resultSet.getString("PHONE"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setAddress(resultSet.getString("ADDRESS"));
            user.setToken(resultSet.getString("TOKEN"));
            user.setCreated_at(resultSet.getTimestamp("CREATED_AT"));
            user.setUpdated_at(resultSet.getTimestamp("UPDATED_AT"));
            user.setLast_login(resultSet.getTimestamp("LAST_LOGIN"));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}
}
