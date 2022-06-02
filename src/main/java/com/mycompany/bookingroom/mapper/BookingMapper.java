package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.Booking;

public class BookingMapper implements RowMapper<Booking> {

    @Override
    public Booking mapRow(ResultSet resultSet) {
        try {
            Booking booking = new Booking();
            booking.setId(resultSet.getInt("ID"));
            booking.setUser_id(resultSet.getInt("USER_ID"));
            booking.setRoom_id(resultSet.getInt("ROOM_ID"));
            booking.setQty(resultSet.getInt("QTY"));
            booking.setCreated_at(resultSet.getTimestamp("CREATED_AT"));
            booking.setUpdated_at(resultSet.getTimestamp("UPDATED_AT"));
            booking.setCheck_in(resultSet.getTimestamp("CHECK_IN"));
            booking.setCheck_out(resultSet.getTimestamp("CHECK_OUT"));
            booking.setDiscount_percent(resultSet.getDouble("DISCOUNT_PERCENT"));
            booking.setStatus(resultSet.getInt("STATUS"));
            return booking;
        } catch (SQLException ex) {
            Logger.getLogger(BookingMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
