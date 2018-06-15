package com.codeup.adlister.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpdateServlet", urlPatterns = "/updateuser")
public class UpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int choice = Integer.parseInt(request.getParameter("update"));
        if (choice == 1) {
            request.getRequestDispatcher("/WEB-INF/users/UPDuser.jsp").forward(request, response);
        } else if (choice == 2) {
            request.getRequestDispatcher("/WEB-INF/users/UPDemail.jsp").forward(request, response);
        } else if (choice == 3) {
            request.getRequestDispatcher("WEB-INF/users/UPDpassword.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
        }
    }
}