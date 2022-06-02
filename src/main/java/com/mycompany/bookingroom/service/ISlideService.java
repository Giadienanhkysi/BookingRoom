package com.mycompany.bookingroom.service;

import java.util.List;

import com.mycompany.bookingroom.model.Slide;

public interface ISlideService {
    List<Slide> findAll();
    Slide findById(Integer id);
    Slide save(Slide slide);
    Slide update(Slide slide);    
    void delete(Integer id);
}
