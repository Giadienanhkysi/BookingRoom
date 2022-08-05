package com.mycompany.bookingroom.controller.web.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Slide;
import com.mycompany.bookingroom.model.SlideImage;
import com.mycompany.bookingroom.service.ISlideService;
import com.mycompany.bookingroom.util.CheckUtil;
import javax.inject.Inject;

@WebServlet(name = "SlideAPI", urlPatterns = {"/api-slide"})
public class SlideAPI extends HttpServlet {

    @Inject
    private ISlideService slideService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String group = request.getParameter("group");
        try ( OutputStream out = response.getOutputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            List<SlideImage> slides = null;
            if(CheckUtil.isInteger(group)){
                slides = (List<SlideImage>) slideService.findSlideImageByGroup(Integer.parseInt(group));
            }
            mapper.writeValue(out, slides);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
