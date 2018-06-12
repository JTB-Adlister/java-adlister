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
import java.util.ArrayList;
import java.util.List;

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

        if (DaoFactory.getUsersDao().userExists(username)) {
            User user = (User) DaoFactory.getSqlDao().findBySearch("users", "username", username);

//            User user = DaoFactory.getUsersDao().findByUsername(username);


            String hash = user.getPassword();

            boolean passwordsDoMatch = BCrypt.checkpw(password, hash);


            System.out.println();
            if (passwordsDoMatch) {
                long id = user.getId();
                request.getSession().setAttribute("errorMessage", null);
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("userId", id);
                request.getSession().setAttribute("username", user.getUsername());
                request.getSession().setAttribute("role", user.getRole());
                System.out.println("User Role is " + user.getRole());

                if (pagename == null) {
                    pagename = "profile";
                }

                response.sendRedirect(pagename);

            } else {
                request.getSession().setAttribute("errorMessage", null);
                List<String> errors = new ArrayList<>();
                errors.add("Incorrect Password");
                request.getSession().setAttribute("errorMessage", errors);
                response.sendRedirect("/login");
            }
        } else {
            request.getSession().setAttribute("errorMessage", null);
            List<String> errors = new ArrayList<>();
            errors.add("User does not exist");
            request.getSession().setAttribute("errorMessage", errors);
            response.sendRedirect("/register");
        }
    }
}