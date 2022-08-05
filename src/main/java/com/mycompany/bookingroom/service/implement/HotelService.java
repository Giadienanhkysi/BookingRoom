package com.mycompany.bookingroom.service.implement;
import com.mycompany.bookingroom.dao.IHotelDAO;
import com.mycompany.bookingroom.model.Hotel;
import com.mycompany.bookingroom.service.IHotelService;
import java.util.List;
import javax.inject.Inject;

public class HotelService implements IHotelService{
    
    @Inject
    private IHotelDAO hotelDAO;
    @Override
    public List<Hotel> findAll() {
        return hotelDAO.findAll();
    }

    @Override
    public List<Hotel> findAllByLocation(String location) {
        return hotelDAO.findAllByLocation(location);
    }
    
    @Override
    public Hotel findById(Integer id) {
        return hotelDAO.findById(id);
    }

    @Override
    public void update(Hotel hm) {
        hotelDAO.update(hm);
    }

    @Override
    public void delete(Integer id) {
        hotelDAO.delete(id);
    }

    @Override
    public Integer save(Hotel hm) {
        return hotelDAO.save(hm);
    }

    @Override
    public List<Hotel> findAllByNameOrLocation(String text) {
        return hotelDAO.findAllByNameOrLocation(text);
    }

    
    
}
