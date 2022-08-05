package com.mycompany.bookingroom.mapper;

import com.mycompany.bookingroom.model.Hotel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//chuyển hotel trong bảng thành đối tượng hotel
public class HotelMapper implements RowMapper<Hotel>{

    @Override
    public Hotel mapRow(ResultSet resultSet) {
        try {
            Hotel hotel = new Hotel();
            hotel.setId(resultSet.getInt("ID"));
            hotel.setName(resultSet.getString("NAME"));
            hotel.setAddress(resultSet.getString("ADDRESS"));
            hotel.setDescription(resultSet.getString("DESCRIPTION"));
            hotel.setStar(resultSet.getInt("STAR"));
            hotel.setPhone(resultSet.getString("PHONE"));
            return hotel;
        } catch (SQLException ex) {
            Logger.getLogger(HotelMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
