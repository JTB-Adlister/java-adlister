package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLUsersDao;
import com.codeup.adlister.dao.Users;
import com.codeup.adlister.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        Boolean isError;
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message;
        int numberOfRounds = 12;
        password = BCrypt.hashpw(password, BCrypt.gensalt(numberOfRounds));

         // make sure the user did not use a username that is already in use.
        if (DaoFactory.getUsersDao().userExists(username)){
            isError = true;
            request.getSession().setAttribute("isError", isError);
            message = "That username already exists. Try again.";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("/register");
        } else {
         // create and save a new user
            DaoFactory.getUsersDao().insert(new User(0, username, email, password));
            User user = DaoFactory.getUsersDao().findByUsername(username);
            long id = user.getId();
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userId", id);
            request.getSession().setAttribute("username", user.getUsername());
            response.sendRedirect("/profile");
        }
    }
}