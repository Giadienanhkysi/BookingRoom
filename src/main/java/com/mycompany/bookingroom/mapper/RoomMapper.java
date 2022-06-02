package com.mycompany.bookingroom.mapper;

import com.mycompany.bookingroom.model.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomMapper implements RowMapper<Room>{

    @Override
    public Room mapRow(ResultSet resultSet) {
        try{
            Room room = new Room();
            room.setId(resultSet.getInt("id"));
            room.setHotel_id(resultSet.getInt("hotel_id"));
            room.setTypes_id(resultSet.getInt("Types_id"));
            room.setName(resultSet.getString("Name"));
            room.setArea(resultSet.getInt("Area"));
            room.setDescription(resultSet.getString("Description"));
            room.setPrice(resultSet.getDouble("Price"));
            room.setPerson_capacity(resultSet.getInt("Person_capacity"));
            room.setToken(resultSet.getString("Token"));
            room.setQty(resultSet.getInt("Qty"));
            room.setDiscount(resultSet.getDouble("discount"));
            room.setCreated_at(resultSet.getTimestamp("Created_at"));
            room.setUpdated_at(resultSet.getTimestamp("Updated_at"));
            
    
            return room;
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
