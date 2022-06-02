package com.mycompany.bookingroom.service.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IAmenitiesDAO;
import com.mycompany.bookingroom.dao.implement.AmenitiesDAO;
import com.mycompany.bookingroom.model.Amenities;
import com.mycompany.bookingroom.service.IAmenitiesService;
import javax.inject.Inject;

public class AmenitiesService implements IAmenitiesService {

    @Inject
    private IAmenitiesDAO amenitiesDAO;

    @Override
    public List<Amenities> findAll() {
        return amenitiesDAO.findAll();
    }

    @Override
    public Amenities findById(Integer id) {
        return amenitiesDAO.findById(id);
    }

    @Override
    public Integer save(Amenities amenities) {
        return amenitiesDAO.save(amenities);
    }

    @Override
    public void update(Amenities amenities) {
        amenitiesDAO.update(amenities);
    }

    @Override
    public void delete(Integer id) {
        amenitiesDAO.delete(id);
    }
}
