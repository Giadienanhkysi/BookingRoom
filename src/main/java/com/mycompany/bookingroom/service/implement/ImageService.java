package com.mycompany.bookingroom.service.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IImageDAO;
import com.mycompany.bookingroom.model.Image;
import com.mycompany.bookingroom.service.IImageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        String fileName = image.getLink();
        try {
            id = imageDAO.save(image);
            imgPart.write(realPath + "/" + fileName);
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
    public Image findBySlideId(Integer id) {
        return imageDAO.findBySlideId(id);
    }

    
}
