package com.mycompany.bookingroom.service;

import java.util.List;

import com.mycompany.bookingroom.model.Rating;

public interface IRatingService {

    List<Rating> findAll();

    Rating findById(Integer id);

    Rating save(Rating rating);

    Rating update(Rating rating);

    void delete(Integer id);
}
