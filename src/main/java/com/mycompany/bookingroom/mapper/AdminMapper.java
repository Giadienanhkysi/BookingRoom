package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.Admin;

public class AdminMapper implements RowMapper<Admin>{

	@Override
	public Admin mapRow(ResultSet resultSet) {
	try {
            Admin admin = new Admin();
            admin.setId(resultSet.getInt("ID"));
            admin.setUsername(resultSet.getString("USERNAME"));
            admin.setPassword(resultSet.getString("PASSWORD"));            
            admin.setFirst_name(resultSet.getString("FIRST_NAME"));
            admin.setLast_name(resultSet.getString("LAST_NAME"));
            admin.setPhone(resultSet.getString("PHONE"));
            admin.setEmail(resultSet.getString("EMAIL"));
            admin.setAddress(resultSet.getString("ADDRESS"));
            admin.setToken(resultSet.getString("TOKEN"));
            admin.setCreated_at(resultSet.getTimestamp("CREATED_AT"));
            admin.setUpdated_at(resultSet.getTimestamp("UPDATED_AT"));
            admin.setLast_login(resultSet.getTimestamp("LAST_LOGIN"));
            return admin;
        } catch (SQLException ex) {
            Logger.getLogger(AdminMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}
}
