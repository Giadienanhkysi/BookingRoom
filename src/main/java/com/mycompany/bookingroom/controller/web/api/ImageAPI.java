package com.mycompany.bookingroom.controller.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Image;
import com.mycompany.bookingroom.service.implement.ImageService;
import com.mycompany.bookingroom.util.CheckUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hensh
 */
@WebServlet(name = "ImageDAO", urlPatterns = {"/api-image"})
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
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
