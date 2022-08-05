package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.Image;

public class ImageMapper implements RowMapper<Image>{

	@Override
	public Image mapRow(ResultSet resultSet) {
		try {
            Image image = new Image();
            image.setId(resultSet.getInt("ID"));
            image.setRoom_id(resultSet.getInt("ROOM_ID"));
            image.setHotel_id(resultSet.getInt("HOTEL_ID"));
            image.setSlide_id(resultSet.getInt("SLIDE_ID"));
            image.setLink(resultSet.getString("LINK"));
            return image;
        } catch (SQLException ex) {
            Logger.getLogger(ImageMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}
}
