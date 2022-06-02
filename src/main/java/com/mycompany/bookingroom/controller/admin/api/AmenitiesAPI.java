package com.mycompany.bookingroom.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Amenities;
import com.mycompany.bookingroom.service.IAmenitiesService;
import com.mycompany.bookingroom.util.CheckUtil;
import com.mycompany.bookingroom.util.JsonUtil;
import java.io.BufferedReader;
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
@WebServlet(name = "AdminAmenitiesAPI", urlPatterns = {"/api-admin-amenities"})
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {            
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Amenities amenities = JsonUtil.toJsonUtil(reader).toModel(Amenities.class);
        Integer id = amenitiesService.save(amenities);
        amenities.setId(id);
        mapper.writeValue(response.getOutputStream(), amenities);
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Amenities amenities = JsonUtil.toJsonUtil(reader).toModel(Amenities.class);
        amenitiesService.update(amenities);
        mapper.writeValue(response.getOutputStream(), amenities);
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader =  request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Amenities amenities = JsonUtil.toJsonUtil(reader).toModel(Amenities.class);
        amenitiesService.delete(amenities.getId());
        mapper.writeValue(response.getOutputStream(), "{}");
    }
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
