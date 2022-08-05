
package com.mycompany.bookingroom.service;

import com.mycompany.bookingroom.model.Hotel;
import java.util.List;


public interface IHotelService {
    List<Hotel> findAll();
    List<Hotel> findAllByLocation(String location);
    List<Hotel> findAllByNameOrLocation(String text);    
    Hotel findById(Integer id);
    Integer save(Hotel hotel);
    void update(Hotel hotel);    
    void delete(Integer id);
//    void findThumnail();
}
