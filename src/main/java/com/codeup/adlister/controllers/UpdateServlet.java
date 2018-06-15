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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.UpdateServlet", urlPatterns = "/updateuser")
public class UpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/users/update.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String passNew = request.getParameter("passNew");
        String passCheck = request.getParameter("confirmPass");
        String passOld = request.getParameter("passOld");
        User user = (User) request.getSession().getAttribute("user");
        String hash = user.getPassword();
        boolean nameExists = DaoFactory.getUsersDao().userExists(username);
        boolean rightPass = Password.check(passOld, hash);
        boolean matchPass = passNew.equals(passCheck);
        boolean diffPass = passNew.equals(passOld);


        request.getSession().setAttribute("message", null);
        if (nameExists || !rightPass || !matchPass) {
            if (nameExists) {
                String usererror = "That username already exists. Please try again.";
                request.getSession().setAttribute("usermessage", usererror);
            }
            if (!rightPass) {
                String passError = "The old password you entered was invalid. Please try again.";
                request.getSession().setAttribute("passmessage1", passError);
            }
            if (!matchPass) {
                String matchError = "The new passwords did not match. Please try again.";
                request.getSession().setAttribute("passmessage2", matchError);
            }
            if (diffPass) {
                String diffError = "Can't reuse same password. Please try again.";
                request.getSession().setAttribute("samepass", diffError);
            }
            request.getSession().setAttribute("name", username);
            request.getSession().setAttribute("email", email);
            response.sendRedirect("/updateuser");
            return;
        }
        request.getSession().setAttribute("name", null);
        request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("message", null);
        String hashPW = Password.hash(passNew);
        DaoFactory.getUsersDao().updateUser(user, username, email, hashPW);
        User newUser = (User) DaoFactory.getSqlDao().findBySearch("users", "username", username);
        request.getSession().setAttribute("username", newUser.getUsername());
        response.sendRedirect("/profile");
    }
}
