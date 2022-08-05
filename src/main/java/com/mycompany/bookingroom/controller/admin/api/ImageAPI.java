package com.mycompany.bookingroom.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Image;
import com.mycompany.bookingroom.service.implement.ImageService;
import com.mycompany.bookingroom.util.CheckUtil;
import com.mycompany.bookingroom.util.JsonUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author hensh
 */
@WebServlet(name = "AdminImageAPI", urlPatterns = {"/api-admin-image"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024*3,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class ImageAPI extends HttpServlet {       
    
    @Inject
    private ImageService imageService;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String hotelId = request.getParameter("hotelId");
        String roomId = request.getParameter("roomId");
        String slideId = request.getParameter("slideId");
        String qty = request.getParameter("qty");
        if(hotelId != null && CheckUtil.isInteger(hotelId)){
            if(qty != null){
                List<Image> images = imageService.findByHotelId(Integer.parseInt(hotelId));
                if(images != null && images.size() > 0)
                    request.setAttribute("Images", images.get(0));
            }else{
                request.setAttribute("Images", imageService.findByHotelId(Integer.parseInt(hotelId)));
            }
        }else if(roomId!=null && CheckUtil.isInteger(roomId)){
            if(qty != null){
                List<Image> images = imageService.findByRoomId(Integer.parseInt(roomId));
                if(images != null && images.size() > 0)
                    request.setAttribute("Images", images.get(0));
            }else{
                request.setAttribute("Images", imageService.findByRoomId(Integer.parseInt(roomId)));
            }
        }
        else if(slideId!=null && CheckUtil.isInteger(slideId)){
            request.setAttribute("Images", imageService.findBySlideId(Integer.parseInt(slideId)));
            
        }
        try(OutputStream out = response.getOutputStream()){
           ObjectMapper mapper = new ObjectMapper();
           mapper.writeValue(out, request.getAttribute("Images"));
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {            
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
//        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        String realPath = request.getServletContext().getRealPath("/images");
        
        List<Image> images = new ArrayList();
        List<Part> imgParts = new ArrayList();
        OutputStream out = response.getOutputStream();
        for(Part part: request.getParts()){
            if(part.getName().equalsIgnoreCase("file")){
                Image image = new Image();
                String hotelId = request.getParameter("hotelId");
                String roomId = request.getParameter("roomId");
                String slideId = request.getParameter("slideId");                
                if(CheckUtil.isInteger(hotelId))
                    image.setHotel_id(Integer.parseInt(hotelId));
                else
                    image.setHotel_id(null);
                if(CheckUtil.isInteger(roomId))
                    image.setRoom_id(Integer.parseInt(roomId));
                else
                    image.setRoom_id(null);
                if(CheckUtil.isInteger(slideId))
                    image.setSlide_id(Integer.parseInt(slideId));
                else
                    image.setSlide_id(null);
                String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
                image.setLink("images/" + fileName.replaceAll("[\\(\\)\\s+]", ""));
                images.add(image);
                imgParts.add(part);
            }
        }
        for (int i = 0; i < imgParts.size(); i++) {
            
            Image image = images.get(i);
            Part imgPart = imgParts.get(i);
            imageService.save(image, imgPart, realPath);            
        }       
        mapper.writeValue(out, images);
    }   

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader =  request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        String realPath = request.getServletContext().getRealPath("/images");
        realPath = realPath.split("images")[0];
        String hotelId = request.getParameter("hotelId");
        String roomId = request.getParameter("roomId");
        String slideId = request.getParameter("slideId");
        
        if(CheckUtil.isInteger(hotelId)){
            imageService.deleteAllImageByForeignKey(Integer.parseInt(hotelId), realPath, "hotel_id");
        }else if(CheckUtil.isInteger(roomId)){
            imageService.deleteAllImageByForeignKey(Integer.parseInt(roomId), realPath, "room_id");
        }else if(CheckUtil.isInteger(slideId)){
            imageService.deleteAllImageByForeignKey(Integer.parseInt(slideId), realPath, "slide_id");
        }
        else{
            Image image = JsonUtil.toJsonUtil(reader).toModel(Image.class);
            imageService.delete(image.getId(),realPath);
        }
        mapper.writeValue(response.getOutputStream(), "{}");
    }
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
