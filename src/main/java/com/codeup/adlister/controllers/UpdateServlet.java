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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String passNew = request.getParameter("passNew");
        String passCheck = request.getParameter("confirmPass");
        String passOld = request.getParameter("passOld");
        User user = (User) request.getSession().getAttribute("user");
        String hash = user.getPassword();

        if (DaoFactory.getUsersDao().userExists(username)){
            request.getSession().setAttribute("message", null);
            List<String> errors = new ArrayList<>();
            errors.add("That username already exists. Please try again");
            request.getSession().setAttribute("message", errors);
            response.sendRedirect("/updateuser");
            return;
        }

        if (!Password.check(passOld, hash)) {
            request.getSession().setAttribute("message", null);
            List<String> errors = new ArrayList<>();
            errors.add("The old password you entered was invalid. Please try again.");
            request.getSession().setAttribute("message", errors);
            response.sendRedirect("/updateuser");
            return;
        }

        if (!passNew.equals(passCheck)) {
            request.getSession().setAttribute("message", null);
            List<String> errors = new ArrayList<>();
            errors.add("The new passwords entered did not match.");
            request.getSession().setAttribute("message", errors);
            response.sendRedirect("/updateuser");
            return;
        }

        String hashPW = Password.hash(passNew);
        DaoFactory.getUsersDao().updateUser(user, username, email, hashPW);
        User newUser = DaoFactory.getUsersDao().findByUsername(username);
        request.getSession().setAttribute("username", newUser.getUsername());
        response.sendRedirect("/profile");
    }
}
