package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpdateEmailServlet", urlPatterns = "/updateemail")
public class UpdateEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String passOld = request.getParameter("passOld");
        User user = (User) request.getSession().getAttribute("user");
        String hash = user.getPassword();
        boolean rightPass = Password.check(passOld, hash);
        if (!rightPass) {
            String passError = "The old password you entered was invalid. Please try again.";
            request.getSession().setAttribute("passmessage1", passError);
            request.getSession().setAttribute("email", email);
            response.sendRedirect("/updateuser");
            return;
        }
        DaoFactory.getUsersDao().updateUser(user, user.getUsername(), email, user.getPassword());
        response.sendRedirect("/profile");
    }
}
