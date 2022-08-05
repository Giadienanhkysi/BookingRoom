package com.mycompany.bookingroom.service.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IImageDAO;
import com.mycompany.bookingroom.model.Image;
import com.mycompany.bookingroom.service.IImageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.Part;

public class ImageService implements IImageService{
	
    @Inject
    private IImageDAO imageDAO;


    @Override
    public List<Image> findAll() {
        return imageDAO.findAll();
    }
    
    @Override
    public List<Image> findByHotelId(Integer id) {
        return imageDAO.findByHotelId(id);
    }

    @Override
    public List<Image> findByRoomId(Integer id) {
        return imageDAO.findByRoomId(id);
    }

    @Override
    public Image findById(Integer id) {
        return imageDAO.findById(id);
    }

    @Override
    public Integer save(Image image, Part imgPart, String realPath ) {             
        Integer id =null;
        if(!Files.exists(Path.of(realPath))){
            try {
                Files.createDirectories(Path.of(realPath));
            } catch (IOException ex) {
                Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        String fileName = image.getLink().split("images")[1]; //'images/anh1.jpg' => ['', /anh1.jpg]
        try {
            id = imageDAO.save(image);
            imgPart.write(realPath + fileName);
        } catch (IOException ex) {
                Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    

    @Override
    public void delete(Integer id, String realPath) {
        String link = imageDAO.findById(id).getLink();        
        try {
            Files.delete(Path.of(realPath  +"/" + link));
            imageDAO.delete(id);
        } catch (IOException ex) {
            Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Image> findBySlideId(Integer id) {
        return imageDAO.findBySlideId(id);
    }

    @Override
    public void deleteAllImageByForeignKey(Integer id, String realPath, String option) {
        List<Image> images = new ArrayList<Image>();
        
        if(option.equalsIgnoreCase("hotel_id")){
             images = imageDAO.findByHotelId(id);
        }else if(option.equalsIgnoreCase("room_id")){
            images = imageDAO.findByRoomId(id);
        }else if(option.equalsIgnoreCase("slide_id")){
            images = imageDAO.findBySlideId(id);
        }
        for(Image image: images){
            String link = image.getLink();
            try {
                Files.delete(Path.of(realPath  +"/" + link));
            } catch (IOException ex) {
                Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        imageDAO.deleteAllImageByForeignKey(id, option);
    }

    
    
}
