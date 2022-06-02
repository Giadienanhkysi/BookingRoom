package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.Amenities;

public class AmenitiesMapper implements RowMapper<Amenities>{
    @Override
    public Amenities mapRow(ResultSet resultSet) {
        try {
            Amenities amenities = new Amenities();
            amenities.setId(resultSet.getInt("ID"));
            amenities.setAmenities(resultSet.getString("AMENITIES"));
            return amenities;
        } catch (SQLException ex) {
            Logger.getLogger(AmenitiesMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
