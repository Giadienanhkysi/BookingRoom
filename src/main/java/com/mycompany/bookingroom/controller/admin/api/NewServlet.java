package com.mycompany.bookingroom.controller.admin.api;

import com.mycompany.bookingroom.model.Hotel;
import com.mycompany.bookingroom.service.IHotelService;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "NewServlet", urlPatterns = {"/test-save"})
public class NewServlet extends HttpServlet {
 
    
    @Inject
    private IHotelService hotelService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String checkin = request.getParameter("checkIn");
        String checkout = request.getParameter("checkOut");        
        out.write(checkin + " " + checkout);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
