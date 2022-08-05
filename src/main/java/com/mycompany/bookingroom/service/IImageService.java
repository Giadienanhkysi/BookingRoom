package com.mycompany.bookingroom.service;

import java.util.List;

import com.mycompany.bookingroom.model.Image;
import javax.servlet.http.Part;


public interface IImageService {
    List<Image> findAll();
    Image findById(Integer id);
    List<Image> findBySlideId(Integer id);
    List<Image> findByHotelId(Integer id);
    List<Image> findByRoomId(Integer id);
    void deleteAllImageByForeignKey(Integer id, String realPath, String option);
    Integer save(Image image, Part imgPart, String realPath);
//    void update(Image image, List<Part> ImgParts, String realPath);
    void delete(Integer id, String realPath);
}
