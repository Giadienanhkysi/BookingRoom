package com.mycompany.bookingroom.controller.web.nav;

import com.mycompany.bookingroom.model.User;
import com.mycompany.bookingroom.service.IUserService;
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
@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class UserLogin extends HttpServlet {

    @Inject
    private IUserService userService;

    private static final long serialVersionUID = 2686801510274002166L;

    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeValue(request, "USER");
            response.sendRedirect(request.getContextPath() + "/trang-chu");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            BufferedReader reader = request.getReader();
            User user = JsonUtil.toJsonUtil(reader).toModel(User.class);
            user = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (user != null) {
                SessionUtil.getInstance().putValue(request, "USER", user);
                response.sendRedirect(request.getContextPath() + "/trang-chu");
            } else {
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=invalid");
            }
        }
    }
}
