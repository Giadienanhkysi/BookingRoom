package com.mycompany.bookingroom.service.implement;

import com.mycompany.bookingroom.dao.IRoomDAO;
import com.mycompany.bookingroom.model.Room;
import com.mycompany.bookingroom.service.IRoomService;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;


public class RoomService implements IRoomService{
    @Inject
    private IRoomDAO roomDAO;
    
    @Override
    public List<Room> findAll() {
        return roomDAO.findAll();
    }

    @Override   
    public List<Room> findAllByHotel(int id) {
        return roomDAO.findAllByHotel(id);
    }

    @Override   
    public List<Room> findAllByDateAndHotel(int id, String checkIn, String checkOut) {
        return roomDAO.findAllByDateAndHotel(id, checkIn, checkOut);
    }
    
    @Override
    public Room findById(Integer id) {
        return roomDAO.findById(id);
    }

    @Override
    public Room update(Room newRoom) {
        Room oldRoom = findById(newRoom.getId());
        if(oldRoom !=null){
            newRoom.setCreated_at(oldRoom.getCreated_at());        
            newRoom.setUpdated_at(new Timestamp(System.currentTimeMillis()));        
            roomDAO.update(newRoom);
            return roomDAO.findById(newRoom.getId());           
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        roomDAO.delete(id);
    }

    @Override
    public Integer save(Room hm) {
        return roomDAO.save(hm);
    }    

}
