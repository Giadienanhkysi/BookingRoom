package com.mycompany.bookingroom.controller.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Type;
import com.mycompany.bookingroom.service.ITypeService;
import com.mycompany.bookingroom.util.CheckUtil;
import java.io.IOException;
import java.io.OutputStream;
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
@WebServlet(name = "TypeAPI", urlPatterns = {"/api-type"})
public class TypeAPI extends HttpServlet {       
    
    @Inject
    private ITypeService typeService;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setAttribute("types", typeService.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String id = request.getParameter("id");
        try(OutputStream out = response.getOutputStream()){
            if(CheckUtil.isInteger(id)){
                Type types  = (Type) typeService.findById(Integer.parseInt(id));
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(out, types);
            }
        }
    }

   
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
