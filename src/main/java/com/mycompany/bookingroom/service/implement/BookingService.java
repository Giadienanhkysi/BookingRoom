package com.mycompany.bookingroom.service.implement;

import java.sql.Timestamp;
import java.util.List;

import com.mycompany.bookingroom.dao.IBookingDAO;
import com.mycompany.bookingroom.model.Booking;
import com.mycompany.bookingroom.model.BookingDetails;
import com.mycompany.bookingroom.service.IBookingService;
import javax.inject.Inject;

public class BookingService implements IBookingService {

    @Inject
    private IBookingDAO bookingDAO;

    @Override
    public List<Booking> findAll(Object ...params) {
        return bookingDAO.findAll(params);
    }

    
    @Override
    public Booking findById(Integer id) {
        return bookingDAO.findById(id);
    }

    @Override
    public Booking save(Booking booking) {
        Integer id = bookingDAO.save(booking);
        return bookingDAO.findById(id);
    }

    @Override
    public Booking update(Booking booking) {
        Booking oldBooking = findById(booking.getId());
        if(oldBooking!=null){
            booking.setCreated_at(oldBooking.getCreated_at());
            booking.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            bookingDAO.update(booking);
            return bookingDAO.findById(booking.getId());            
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        bookingDAO.delete(id);
    }

    @Override
    public List<Booking> findByUserId(Integer id, Object ...params) {
        return bookingDAO.findByUserId(id, params);
    }
    @Override
    public List<BookingDetails> findAllBookingDetails(Object ...params){
        return  bookingDAO.findAllBookingDetails(params);
    }

}
