
package com.mycompany.bookingroom.dao;

import com.mycompany.bookingroom.model.Amenities;
import com.mycompany.bookingroom.model.Amenities_Rooms;
import java.util.List;


public interface IAmenities_RoomsDAO {
    List<Amenities_Rooms> findAll();
    Amenities_Rooms findById(Integer id);
    List<Amenities_Rooms> findByRoomId(Integer id);
    List<Amenities> findAmenitiesByRoomId(Integer id);
    Integer save(Amenities_Rooms ar);
    void update(Amenities_Rooms ar);
    void delete(Amenities_Rooms ar);
    void deleteByRoomId(Integer roomId);

}
