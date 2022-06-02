package com.mycompany.bookingroom.controller.admin.api;

import java.io.BufferedReader;
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
import com.mycompany.bookingroom.service.ISlideService;
import javax.inject.Inject;
import com.mycompany.bookingroom.util.JsonUtil;

@WebServlet(name = "AdminSlideAPI", urlPatterns = {"/api-admin-slide"})
public class SlideAPI extends HttpServlet {

    @Inject
    private ISlideService slideService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("slides", slideService.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try ( OutputStream out = response.getOutputStream()) {
            List<Slide> slides = (List<Slide>) request.getAttribute("slides");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, slides);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Slide slide = JsonUtil.toJsonUtil(reader).toModel(Slide.class);
        mapper.writeValue(response.getOutputStream(), slideService.save(slide));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Slide slide = JsonUtil.toJsonUtil(reader).toModel(Slide.class);
        mapper.writeValue(response.getOutputStream(), slideService.update(slide));

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Slide slide = JsonUtil.toJsonUtil(reader).toModel(Slide.class);
        slideService.delete(slide.getId());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
