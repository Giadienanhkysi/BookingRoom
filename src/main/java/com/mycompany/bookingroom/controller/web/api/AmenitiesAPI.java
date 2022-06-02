package com.mycompany.bookingroom.controller.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Amenities;
import com.mycompany.bookingroom.service.IAmenitiesService;
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


@WebServlet(name = "AmenitiesAPI", urlPatterns = {"/api-amenities"})
public class AmenitiesAPI extends HttpServlet {

    @Inject
    private IAmenitiesService amenitiesService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("amenities", amenitiesService.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String id = request.getParameter("id");
        try(PrintWriter out = response.getWriter()){
            ObjectMapper mapper = new ObjectMapper();
            if(CheckUtil.isInteger(id)){
                Amenities amenitiesX  = (Amenities) amenitiesService.findById(Integer.parseInt(id));
                mapper.writeValue(out, amenitiesX);
            }else{
                List<Amenities> amenities  = (List<Amenities>) request.getAttribute("amenities");
                mapper.writeValue(out, amenities);
                
            }
        
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
