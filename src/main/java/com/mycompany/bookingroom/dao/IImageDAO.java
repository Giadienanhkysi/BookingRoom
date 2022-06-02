package com.mycompany.bookingroom.dao;

import java.util.List;

import com.mycompany.bookingroom.model.Image;

public interface IImageDAO {
    List<Image> findAll();
    Image findById(Integer id);
    Image findBySlideId(Integer id);    
    List<Image> findByHotelId(Integer id);
    List<Image> findByRoomId(Integer id);
    Integer save(Image image);
    void update(Image image);
    void delete(Integer id);
}
