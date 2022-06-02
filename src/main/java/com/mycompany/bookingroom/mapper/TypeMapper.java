package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.Type;

public class TypeMapper implements RowMapper<Type>{

	@Override
	public Type mapRow(ResultSet resultSet) {
		try {
            Type type = new Type();
            type.setId(resultSet.getInt("ID"));
            type.setName(resultSet.getString("NAME"));
            return type;
        } catch (SQLException ex) {
            Logger.getLogger(TypeMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}
}
