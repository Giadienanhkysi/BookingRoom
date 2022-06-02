package com.mycompany.bookingroom.controller.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Room;
import com.mycompany.bookingroom.service.IRoomService;
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


@WebServlet(name = "RoomAPI", urlPatterns = {"/api-room"})
public class RoomAPI extends HttpServlet {

    @Inject
    private IRoomService roomService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try(OutputStream out = response.getOutputStream()){
            String hotelId = request.getParameter("hotelId");
            String checkIn = request.getParameter("checkIn");
            String checkOut = request.getParameter("checkOut");
            String roomId = request.getParameter("roomId");
            ObjectMapper mapper = new ObjectMapper();
            if(CheckUtil.isInteger(roomId)){
                Room room = roomService.findById(Integer.parseInt(roomId));
                mapper.writeValue(out, room);
            }
            else if(hotelId != null){
                try{
                    List<Room> rooms= null;
                    if(checkIn != null && checkOut != null){
                        rooms = roomService.findAllByDateAndHotel(Integer.parseInt(hotelId), checkIn, checkOut);
                    }else{
                        //trả về phòng của khách sạn chỉ cần hotelId
                        if(CheckUtil.isInteger(hotelId))
                        rooms = roomService.findAllByHotel(Integer.parseInt(hotelId));                       
                    }
                    mapper.writeValue(out, rooms);
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
        

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
