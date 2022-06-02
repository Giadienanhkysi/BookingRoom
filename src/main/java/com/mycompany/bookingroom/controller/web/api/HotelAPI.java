package com.mycompany.bookingroom.controller.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Hotel;
import com.mycompany.bookingroom.service.IHotelService;
import com.mycompany.bookingroom.util.CheckUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "HotelAPI", urlPatterns = {"/api-hotel"})
public class HotelAPI extends HttpServlet {    

    @Inject
    private IHotelService hotelService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try(OutputStream out = response.getOutputStream()){
            String id = request.getParameter("id");
            String location = request.getParameter("location");
            if(id != null){ //n
                getHotelById(id, out);
            }else{
                getHotelsByLocation(location, out);                
            }
        }
    }
    
   

    private void getHotelsByLocation(String location, OutputStream out) throws ServletException, IOException{
            List<Hotel> hotels;
            if (location == null){
                hotels = hotelService.findAll() ;
            }else{
                hotels = hotelService.findAllByLocation(location);
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, hotels);        
    }
    private void getHotelById(String id, OutputStream out) throws IOException{
            try{
                if(CheckUtil.isInteger(id)){
                    Hotel hotel =(Hotel) hotelService.findById(Integer.parseInt(id));
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writeValue(out, hotel);
                }
            }catch(IOException | NumberFormatException ex){
                ex.printStackTrace();
            }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
