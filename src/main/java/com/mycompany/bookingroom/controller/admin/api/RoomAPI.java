package com.mycompany.bookingroom.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Room;
import com.mycompany.bookingroom.service.IRoomService;
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

/**
 *
 * @author hensh
 */
@WebServlet(name = "AdminRoomAPI", urlPatterns = {"/api-admin-room"})
public class RoomAPI extends HttpServlet {
    
    @Inject
    private IRoomService roomService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try(OutputStream out = response.getOutputStream()){
            String hotelId = request.getParameter("hotelId");
            if(hotelId != null){
                try{
                    List<Room> rooms = roomService.findAllByHotel(Integer.parseInt(hotelId));
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writeValue(out, rooms);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Room room = JsonUtil.toJsonUtil(reader).toModel(Room.class);
        Integer id = roomService.save(room);
        room.setId(id);
        mapper.writeValue(response.getOutputStream(), room);
    }

    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Room room = JsonUtil.toJsonUtil(reader).toModel(Room.class);
        room = roomService.update(room);
        mapper.writeValue(response.getOutputStream(), room);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Room room = JsonUtil.toJsonUtil(reader).toModel(Room.class);
        roomService.delete(room.getId());
        mapper.writeValue(response.getOutputStream(), "{}");
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
