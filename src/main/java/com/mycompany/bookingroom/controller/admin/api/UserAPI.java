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
import com.mycompany.bookingroom.model.User;
import com.mycompany.bookingroom.service.IUserService;
import javax.inject.Inject;
import com.mycompany.bookingroom.util.JsonUtil;

@WebServlet(name = "UserAPI", urlPatterns = {"/api-admin-user"})
public class UserAPI extends HttpServlet {

    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("users", userService.findAll());
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try ( OutputStream out = response.getOutputStream()) {
            List<User> users = (List<User>) request.getAttribute("users");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, users);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        User user = JsonUtil.toJsonUtil(reader).toModel(User.class);
        mapper.writeValue(response.getOutputStream(), userService.save(user));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        User user = JsonUtil.toJsonUtil(reader).toModel(User.class);
        mapper.writeValue(response.getOutputStream(), userService.update(user));

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        User user = JsonUtil.toJsonUtil(reader).toModel(User.class);
        userService.delete(user.getId());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
