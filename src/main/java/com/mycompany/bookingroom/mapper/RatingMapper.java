package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.Rating;

public class RatingMapper implements RowMapper<Rating>{

	@Override
	public Rating mapRow(ResultSet resultSet) {
		try {
            Rating rating = new Rating();
            rating.setId(resultSet.getInt("ID"));
            rating.setUser_id(resultSet.getInt("USER_ID"));
            rating.setRoom_id(resultSet.getInt("ROOM_ID"));
            rating.setStar(resultSet.getInt("STAR"));
            rating.setContent(resultSet.getString("CONTENT"));
            rating.setCreated_at(resultSet.getTimestamp("CREATED_AT"));
            rating.setUpdated_at(resultSet.getTimestamp("UPDATED_AT"));
            return rating;
        } catch (SQLException ex) {
            Logger.getLogger(RatingMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}
}
