package com.mycompany.bookingroom.dao;

import java.util.List;

import com.mycompany.bookingroom.model.Rating;

public interface IRatingDAO {
	List<Rating> findAll();
    Rating findById(Integer id);
    Integer save(Rating rating);
    void update(Rating rating);   
    void delete(Integer id);
}
