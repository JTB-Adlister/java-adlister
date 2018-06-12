package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.UpdateServlet", urlPatterns = "/updateuser")
public class UpdateServlet extends HttpServlet {
    protected void doGet(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String passNew = Password.hash(request.getParameter("passNew"));
        String passCheck = Password.hash(request.getParameter("confirmPass"));
        String passOld = Password.hash(request.getParameter("passOld"));

        if (DaoFactory.getUsersDao().userExists(username)){
            request.getSession().setAttribute("message", null);
            List<String> errors = new ArrayList<>();
            errors.add("That username already exists. Please try again");
            request.getSession().setAttribute("message", errors);
            response.sendRedirect("/register");
        }
    }
}
