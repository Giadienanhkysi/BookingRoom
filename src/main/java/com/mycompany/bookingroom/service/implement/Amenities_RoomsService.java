package com.mycompany.bookingroom.service.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IAmenities_RoomsDAO;
import com.mycompany.bookingroom.model.Amenities;
import com.mycompany.bookingroom.model.Amenities_Rooms;
import com.mycompany.bookingroom.service.IAmenities_RoomsService;
import javax.inject.Inject;

public class Amenities_RoomsService implements IAmenities_RoomsService {

    @Inject
    private IAmenities_RoomsDAO amenities_RoomsDAO;

    @Override
    public List<Amenities_Rooms> findAll() {
        return amenities_RoomsDAO.findAll();
    }

    @Override
    public Amenities_Rooms findById(Integer id) {
        return amenities_RoomsDAO.findById(id);
    }
    
    @Override
    public List<Amenities_Rooms> findByRoomId(Integer id) {
        return amenities_RoomsDAO.findByRoomId(id);
    }

    

    @Override
    public Integer save(Amenities_Rooms amenities_Rooms) {
        return amenities_RoomsDAO.save(amenities_Rooms);
    }

    @Override
    public void update(Amenities_Rooms amenities_Rooms) {
        amenities_RoomsDAO.update(amenities_Rooms);
    }

    @Override
    public void delete(Amenities_Rooms ar) {
        amenities_RoomsDAO.delete(ar);
    }

    @Override
    public List<Amenities> findAmenitiesByRoomId(Integer id) {
        return amenities_RoomsDAO.findAmenitiesByRoomId(id);
    }

    @Override
    public void saveMultiple(List<Amenities_Rooms> amrs) {
        for(Amenities_Rooms ar: amrs){
            save(ar);
        }
    }

    @Override
    public void deleteByRoomId(Integer roomId) {
        amenities_RoomsDAO.deleteByRoomId(roomId);
    }
}
