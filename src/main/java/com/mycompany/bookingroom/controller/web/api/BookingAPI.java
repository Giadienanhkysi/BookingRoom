package com.mycompany.bookingroom.controller.web.api;

import com.mycompany.bookingroom.controller.admin.api.*;
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
import com.mycompany.bookingroom.model.Booking;
import com.mycompany.bookingroom.service.IBookingService;
import com.mycompany.bookingroom.util.JsonUtil;
import javax.inject.Inject;

@WebServlet(name = "BookingAPI", urlPatterns = {"/api-booking"})
public class BookingAPI extends HttpServlet {

    @Inject
    private IBookingService bookingService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String mode = request.getParameter("mode");
        String column = request.getParameter("column");
        request.setAttribute("booking", bookingService.findAll(mode, column));
        try ( OutputStream out = response.getOutputStream()) {
            List<Booking> booking = (List<Booking>) request.getAttribute("booking");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, booking);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Booking booking = JsonUtil.toJsonUtil(reader).toModel(Booking.class);
        mapper.writeValue(response.getOutputStream(), bookingService.save(booking));
    }

//    @Override
//    protected void doPut(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json;charset=utf-8");
//        BufferedReader reader = request.getReader();
//        ObjectMapper mapper = new ObjectMapper();
//        Booking booking = JsonUtil.toJsonUtil(reader).toModel(Booking.class);
//        mapper.writeValue(response.getOutputStream(), bookingService.update(booking));
//
//    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
