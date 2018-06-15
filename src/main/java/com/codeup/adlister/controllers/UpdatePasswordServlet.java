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

@WebServlet(name = "controllers.UpdatePasswordServlet", urlPatterns = "/updatepassword")
public class UpdatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String passOld = request.getParameter("passOld");
        User user = (User) request.getSession().getAttribute("user");
        String hash = user.getPassword();
        String passNew = request.getParameter("passNew");
        String passCheck = request.getParameter("confirmPass");
        boolean rightPass = Password.check(passOld, hash);
        boolean matchPass = passNew.equals(passCheck);
        boolean diffPass = passNew.equals(passOld);
        request.getSession().setAttribute("message", null);

        if (!rightPass || !matchPass || diffPass) {
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
            request.getRequestDispatcher("/WEB-INF/users/UPDpassword.jsp").forward(request, response);
            return;
        }
        DaoFactory.getUsersDao().updateUser(user, user.getUsername(), user.getEmail(), Password.hash(passNew));
        request.getSession().setAttribute("username", user.getUsername());
        response.sendRedirect("/profile");
    }
}
