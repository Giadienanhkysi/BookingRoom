package com.mycompany.bookingroom.mapper;

import com.mycompany.bookingroom.model.Amenities_Rooms;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Amenities_RoomsMapper implements RowMapper<Amenities_Rooms>{

    @Override
    public Amenities_Rooms mapRow(ResultSet resultSet) {
        try {
            Amenities_Rooms amenr = new Amenities_Rooms();
            amenr.setRoom_id(resultSet.getInt("Room_id"));
            amenr.setAmenities_id(resultSet.getInt("AMENITIES_id"));
            amenr.setCreated_at(resultSet.getTimestamp("Created_at"));
            return amenr;
        } catch (SQLException ex) {
            Logger.getLogger(Amenities_RoomsMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
