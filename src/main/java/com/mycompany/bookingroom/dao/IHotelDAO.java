package com.mycompany.bookingroom.dao;

import java.util.List;
import com.mycompany.bookingroom.model.Hotel;

public interface IHotelDAO {
    List<Hotel> findAll();
    List<Hotel> findAllByLocation(String location);
    Hotel findById(Integer id);
    Integer save(Hotel hotel);
    void update(Hotel hotel);    
    void delete(Integer id);

}
