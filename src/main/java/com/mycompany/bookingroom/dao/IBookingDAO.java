package com.mycompany.bookingroom.dao;

import java.util.List;

import com.mycompany.bookingroom.model.Booking;
import com.mycompany.bookingroom.model.BookingDetails;

public interface IBookingDAO {
    List<Booking> findAll(Object ...params);
    List<BookingDetails> findAllBookingDetails(Object ...params);
    Booking findById(Integer id);
    List<Booking> findByUserId(Integer id, Object ...params);
    Integer save(Booking booking);
    void update(Booking booking);
    void delete(Integer id);
}
