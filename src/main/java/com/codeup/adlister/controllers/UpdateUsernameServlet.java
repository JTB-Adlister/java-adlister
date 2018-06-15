package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpdateUsernameServlet", urlPatterns = "/updateusername")
public class UpdateUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String passOld = request.getParameter("passOld");
        boolean nameExists = DaoFactory.getUsersDao().userExists(username);
        User user = (User) request.getSession().getAttribute("user");
        String hash = user.getPassword();
        boolean rightPass = Password.check(passOld, hash);
        if (nameExists || !rightPass) {
            if (nameExists) {
                String usererror = "That username already exists. Please try again.";
                request.getSession().setAttribute("usermessage", usererror);
            }
            if (!rightPass) {
                String passError = "The old password you entered was invalid. Please try again.";
                request.getSession().setAttribute("passmessage1", passError);
            }
            request.getSession().setAttribute("name", username);
            request.getRequestDispatcher("/WEB-INF/users/UPDuser.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("username", null);
        DaoFactory.getUsersDao().updateUser(user, username, user.getEmail(), user.getPassword());
        request.getSession().setAttribute("username", username);
        response.sendRedirect("/profile");
    }
}
