
package com.mycompany.bookingroom.dao;
import com.mycompany.bookingroom.model.Room;
import java.util.List;


public interface IRoomDAO {
    List<Room> findAll();
    List<Room> findAllByHotel(int id);
    List<Room> findAllByDateAndHotel(int id, String checkIn, String checkOut);
    Room findById(Integer id);
    Integer save(Room room);
    void update(Room room);
    void delete(Integer id);
}