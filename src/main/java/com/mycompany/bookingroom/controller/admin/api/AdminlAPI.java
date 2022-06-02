package com.mycompany.bookingroom.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Admin;
import com.mycompany.bookingroom.service.implement.AdminService;
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

@WebServlet(name = "AdminAPI", urlPatterns = {"/api-admin-admin"})
public class AdminlAPI extends HttpServlet {       
    
    @Inject
    private AdminService adminService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("admins", adminService.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try(OutputStream out = response.getOutputStream()){         
              List<Admin> admins  = (List<Admin>) request.getAttribute("admins");
              ObjectMapper mapper = new ObjectMapper();
              mapper.writeValue(out, admins);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Admin admin = JsonUtil.toJsonUtil(reader).toModel(Admin.class);
//        adminService.save(admin);        
        //return lai admin vua luu
        mapper.writeValue(response.getOutputStream(), adminService.save(admin));
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Admin newAdmin = JsonUtil.toJsonUtil(reader).toModel(Admin.class);       
//        new AdminDAO().update(newAdmin);
        mapper.writeValue(response.getOutputStream(),adminService.update(newAdmin));
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader =  request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Admin admin = JsonUtil.toJsonUtil(reader).toModel(Admin.class);
        adminService.delete(admin.getId());
        mapper.writeValue(response.getOutputStream(), "{}");
    }
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
