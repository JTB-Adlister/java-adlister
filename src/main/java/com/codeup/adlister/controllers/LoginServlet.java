package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLUsersDao;
import com.codeup.adlister.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String pagename = (String) request.getSession().getAttribute("currentpage");

        User user = DaoFactory.getUsersDao().findByUsername(username);

        int numberOfRounds = 12;
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(numberOfRounds));

        boolean validAttempt = BCrypt.checkpw(password,hash);


        if (validAttempt) {
            long id = user.getId();
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userId", id);
            request.getSession().setAttribute("username", user.getUsername());

            if (pagename == null){
                pagename = "index";
            }

            response.sendRedirect(pagename);

        } else {
            response.sendRedirect("/login");
        }
    }
}