package com.mycompany.bookingroom.dao;

import java.util.List;

import com.mycompany.bookingroom.model.Slide;

public interface ISlideDAO {
    List<Slide> findAll();
    Slide findById(Integer id);
    Integer save(Slide slide);
    void update(Slide slide);   
    void delete(Integer id);
}
