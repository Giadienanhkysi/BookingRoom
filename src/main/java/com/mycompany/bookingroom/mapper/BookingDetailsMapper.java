package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.bookingroom.model.BookingDetails;

public class BookingDetailsMapper implements RowMapper<BookingDetails> {

    @Override
    public BookingDetails mapRow(ResultSet resultSet) {
        try {
            BookingDetails booking = new BookingDetails();
            booking.setId(resultSet.getInt("ID"));
            booking.setUserId(resultSet.getInt("USERID"));
            booking.setRoomId(resultSet.getInt("ROOMID"));
            booking.setHotelId(resultSet.getInt("HotelId"));            
            booking.setFirstName(resultSet.getString("FirstName"));
            booking.setLastName(resultSet.getString("LastName"));            
            booking.setPhone(resultSet.getString("Phone"));
            booking.setHotelName(resultSet.getString("HotelName"));
            booking.setRoomType(resultSet.getString("RoomType"));
            booking.setRoomPrice(resultSet.getDouble("RoomPrice"));
            booking.setQuantity(resultSet.getInt("Quantity"));
            booking.setCreatedAt(resultSet.getTimestamp("CREATED_AT"));
            booking.setUpdatedAt(resultSet.getTimestamp("UPDATED_AT"));
            booking.setCheckIn(resultSet.getTimestamp("CHECK_IN"));
            booking.setCheckOut(resultSet.getTimestamp("CHECK_OUT"));
            booking.setDiscountPercent(resultSet.getDouble("DISCOUNT_PERCENT"));
            booking.setStatus(resultSet.getInt("STATUS"));
            return booking;
        } catch (SQLException ex) {
            Logger.getLogger(BookingDetailsMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
