package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.ViewAllUsersServlet", urlPatterns = "/viewUsers")
public class ViewAllUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getSession().getAttribute("role").equals("admin")) {
                List<Object> users = DaoFactory.getSqlDao().listAll("users", "user");
                request.setAttribute("users", users);
                request.getRequestDispatcher("/WEB-INF/users/userIndex.jsp").forward(request, response);
            } else {
                response.sendRedirect("/index");
            }
        } catch(NullPointerException e){
            response.sendRedirect("index");
        }
    }
}

