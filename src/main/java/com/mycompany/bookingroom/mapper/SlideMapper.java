package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.Slide;

public class SlideMapper implements RowMapper<Slide>{

	@Override
	public Slide mapRow(ResultSet resultSet) {
		try {
            Slide slide = new Slide();
            slide.setId(resultSet.getInt("ID"));
            slide.setLink(resultSet.getString("LINK"));
            slide.setTitle(resultSet.getString("TITLE"));
            slide.setDescription(resultSet.getString("DESCRIPTION"));
            slide.setGroup(resultSet.getInt("GRP"));
            slide.setCreated_at(resultSet.getTimestamp("CREATED_AT"));
            slide.setUpdated_at(resultSet.getTimestamp("UPDATED_AT"));
            return slide;
        } catch (SQLException ex) {
            Logger.getLogger(SlideMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}
}
