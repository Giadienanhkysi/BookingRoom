package com.mycompany.bookingroom.service;

import com.mycompany.bookingroom.model.Amenities;
import java.util.List;

import com.mycompany.bookingroom.model.Amenities_Rooms;

public interface IAmenities_RoomsService {

    List<Amenities_Rooms> findAll();

    Amenities_Rooms findById(Integer id);
    
    List<Amenities_Rooms> findByRoomId(Integer id);
    
    List<Amenities> findAmenitiesByRoomId(Integer id);

    Integer save(Amenities_Rooms amenities_Rooms);
    
    void saveMultiple(List<Amenities_Rooms> amrs);

    void update(Amenities_Rooms amenities_Rooms);

    void delete(Amenities_Rooms ar);

    public void deleteByRoomId(Integer roomId);
}
