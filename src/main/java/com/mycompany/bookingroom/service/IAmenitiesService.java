package com.mycompany.bookingroom.service;

import java.util.List;

import com.mycompany.bookingroom.model.Amenities;


public interface IAmenitiesService {
	List<Amenities> findAll();
    Amenities findById(Integer id);
    Integer save(Amenities amenities);
    void update(Amenities amenities);    
    void delete(Integer id);
}
