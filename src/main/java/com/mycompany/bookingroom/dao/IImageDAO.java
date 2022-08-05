package com.mycompany.bookingroom.dao;

import java.util.List;

import com.mycompany.bookingroom.model.Image;

public interface IImageDAO {
    List<Image> findAll();
    Image findById(Integer id);
    List<Image> findBySlideId(Integer id);    
    List<Image> findByHotelId(Integer id);
    List<Image> findByRoomId(Integer id);
    void deleteAllImageByForeignKey(Integer id, String option);
    Integer save(Image image);
    void update(Image image);
    void delete(Integer id);
}
