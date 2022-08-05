package com.mycompany.bookingroom.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.bookingroom.model.Type;
import com.mycompany.bookingroom.service.ITypeService;
import com.mycompany.bookingroom.util.CheckUtil;
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

/**
 *
 * @author hensh
 */
@WebServlet(name = "AdminTypeAPI", urlPatterns = {"/api-admin-type"})
public class TypeAPI extends HttpServlet {

    @Inject
    private ITypeService typeService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String id = request.getParameter("id");

        try ( OutputStream out = response.getOutputStream()) {
            ObjectMapper mapper = new ObjectMapper();

            if (CheckUtil.isInteger(id)) {
                Type types = (Type) typeService.findById(Integer.parseInt(id));
                mapper.writeValue(out, types);
            } else {
                List<Type> types = (List<Type>) typeService.findAll();
                mapper.writeValue(out, types);

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
        Type type = JsonUtil.toJsonUtil(reader).toModel(Type.class);
        Integer id = typeService.save(type);
        type.setId(id);
        mapper.writeValue(response.getOutputStream(), type);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Type newType = JsonUtil.toJsonUtil(reader).toModel(Type.class);
        typeService.update(newType);
        mapper.writeValue(response.getOutputStream(), newType);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Type type = JsonUtil.toJsonUtil(reader).toModel(Type.class);
        typeService.delete(type.getId());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
