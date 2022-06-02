package com.mycompany.bookingroom.controller.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Amenities;
import com.mycompany.bookingroom.model.Amenities_Rooms;
import com.mycompany.bookingroom.service.IAmenities_RoomsService;
import com.mycompany.bookingroom.util.CheckUtil;
import java.io.IOException;
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
@WebServlet(name = "AmenitiesRoomsAPI", urlPatterns = {"/api-amenities-room"})
public class AmenitiesRoomsAPI extends HttpServlet {

    @Inject
    private IAmenities_RoomsService amenitiesRoomsService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String roomId = request.getParameter("roomId");
        
        try(PrintWriter out = response.getWriter()){
            ObjectMapper mapper = new ObjectMapper();
            if(CheckUtil.isInteger(roomId)){
                List<Amenities> amenitiesOfRooms  = (List<Amenities>) amenitiesRoomsService
                                                        .findAmenitiesByRoomId(Integer.parseInt(roomId));
                mapper.writeValue(out, amenitiesOfRooms);
            }
        
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
