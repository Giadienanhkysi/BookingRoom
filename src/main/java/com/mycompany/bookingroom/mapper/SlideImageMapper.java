package com.mycompany.bookingroom.mapper;

import com.mycompany.bookingroom.model.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.SlideImage;

public class SlideImageMapper implements RowMapper<SlideImage>{

	@Override
	public SlideImage mapRow(ResultSet resultSet) {
	try {
            SlideImage slide = new SlideImage();
            slide.setId(resultSet.getInt("ID"));
            slide.setLink(resultSet.getString("slideLINK"));
            slide.setTitle(resultSet.getString("TITLE"));
            slide.setDescription(resultSet.getString("DESCRIPTION"));
            slide.setGroup(resultSet.getInt("GRP"));
            slide.setCreated_at(resultSet.getTimestamp("CREATED_AT"));
            slide.setUpdated_at(resultSet.getTimestamp("UPDATED_AT"));
            try{
                Image image = new Image();
                image.setId(resultSet.getInt("imageId"));
                image.setHotel_id(resultSet.getInt("hotel_Id"));
                image.setRoom_id(resultSet.getInt("Room_Id"));
                image.setSlide_id(resultSet.getInt("Slide_Id"));
                image.setLink(resultSet.getString("imageLink"));
                slide.setImage(image);
            }catch(SQLException ex) {
                Logger.getLogger(SlideImageMapper.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            return slide;
        } catch (SQLException ex) {
            Logger.getLogger(SlideImageMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}
}
