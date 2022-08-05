package com.mycompany.bookingroom.dao;

import java.util.List;

import com.mycompany.bookingroom.model.Slide;
import com.mycompany.bookingroom.model.SlideImage;

public interface ISlideDAO {
    List<Slide> findAll();
    Slide findById(Integer id);
    List<SlideImage> findSlideImageByGroup(Integer grp);
    Integer save(Slide slide);
    void update(Slide slide);   
    void delete(Integer id);
}
