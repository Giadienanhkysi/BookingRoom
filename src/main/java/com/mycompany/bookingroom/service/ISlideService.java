package com.mycompany.bookingroom.service;

import java.util.List;

import com.mycompany.bookingroom.model.Slide;
import com.mycompany.bookingroom.model.SlideImage;

public interface ISlideService {
    List<Slide> findAll();
    Slide findById(Integer id);
    List<SlideImage> findSlideImageByGroup(Integer grp);
    Slide save(Slide slide);
    Slide update(Slide slide);    
    void delete(Integer id);
}
