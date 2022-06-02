package com.mycompany.bookingroom.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Hotel;
import com.mycompany.bookingroom.service.IHotelService;
import com.mycompany.bookingroom.util.JsonUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminHotelAPI", urlPatterns = {"/api-admin-hotel"})
public class HotelAPI extends HttpServlet {       
    
    @Inject
    private IHotelService hotelService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("hotels", hotelService.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try(OutputStream out = response.getOutputStream()){         
              List<Hotel> hotels  = (List<Hotel>) request.getAttribute("hotels");
              ObjectMapper mapper = new ObjectMapper();
              mapper.writeValue(out, hotels);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Hotel hotel = JsonUtil.toJsonUtil(reader).toModel(Hotel.class);
        Integer id = hotelService.save(hotel);
        hotel.setId(id);
        mapper.writeValue(response.getOutputStream(), hotel);
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Hotel newHotel = JsonUtil.toJsonUtil(reader).toModel(Hotel.class);
        hotelService.update(newHotel);
        mapper.writeValue(response.getOutputStream(), newHotel);
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader =  request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Hotel hotel = JsonUtil.toJsonUtil(reader).toModel(Hotel.class);
        hotelService.delete(hotel.getId());
        mapper.writeValue(response.getOutputStream(), "{}");
    }
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
