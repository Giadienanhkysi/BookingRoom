package com.mycompany.bookingroom.service;

import java.util.List;

import com.mycompany.bookingroom.model.Booking;

public interface IBookingService {

    List<Booking> findAll(Object ...params);

    List<Booking> findByUserId(Integer id, Object ...params);

    Booking findById(Integer id);

    Booking save(Booking booking);

    Booking update(Booking booking);

    void delete(Integer id);
}
