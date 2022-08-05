package com.mycompany.bookingroom.controller.web.nav;

import com.mycompany.bookingroom.model.Admin;
import com.mycompany.bookingroom.service.IAdminService;
import com.mycompany.bookingroom.util.JsonUtil;
import com.mycompany.bookingroom.util.SessionUtil;
import java.io.BufferedReader;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hensh
 */
@WebServlet(urlPatterns = { "/quan-ly", "/dang-nhap-quan-ly", "/dang-xuat-quan-ly"})
public class AdminLogin extends HttpServlet {

    @Inject
    private IAdminService adminService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            RequestDispatcher rd = request.getRequestDispatcher("adminLogin.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeValue(request, "ADMIN");
            response.sendRedirect(request.getContextPath() + "/quan-ly");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/adminLogin.jsp");
            rd.forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            BufferedReader reader = request.getReader();
            Admin admin = JsonUtil.toJsonUtil(reader).toModel(Admin.class);
            admin = adminService.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
            if (admin != null) {
                SessionUtil.getInstance().putValue(request, "ADMIN", admin);
                response.sendRedirect(request.getContextPath() + "/admin.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/dang-nhap-quan-ly?action=invalid");
            }
        }
    }
}
