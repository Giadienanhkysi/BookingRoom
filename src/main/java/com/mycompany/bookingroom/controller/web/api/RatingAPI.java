package com.mycompany.bookingroom.controller.web.api;

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
import com.mycompany.bookingroom.model.Rating;
import javax.inject.Inject;
import com.mycompany.bookingroom.service.implement.RatingService;
import com.mycompany.bookingroom.util.JsonUtil;

@WebServlet(name = "RatingAPI", urlPatterns = {"/api-rating"})
public class RatingAPI extends HttpServlet {

    @Inject
    private RatingService ratingService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ratings", ratingService.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try ( OutputStream out = response.getOutputStream()) {
            List<Rating> ratings = (List<Rating>) request.getAttribute("ratings");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, ratings);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Rating rating = JsonUtil.toJsonUtil(reader).toModel(Rating.class);
        mapper.writeValue(response.getOutputStream(), ratingService.save(rating));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Rating rating = JsonUtil.toJsonUtil(reader).toModel(Rating.class);
        mapper.writeValue(response.getOutputStream(), ratingService.update(rating));

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Rating rating = JsonUtil.toJsonUtil(reader).toModel(Rating.class);
        ratingService.delete(rating.getId());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
