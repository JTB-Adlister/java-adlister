package com.codeup.adlister.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpdateServlet", urlPatterns = "/updateuser")
public class UpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
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
=======
        int choice = Integer.parseInt(request.getParameter("update"));
        if (choice == 1) {
            request.getRequestDispatcher("/WEB-INF/users/UPDuser.jsp").forward(request, response);
        } else if (choice == 2) {
            request.getRequestDispatcher("/WEB-INF/users/UPDemail.jsp").forward(request, response);
        } else if (choice == 3) {
            request.getRequestDispatcher("WEB-INF/users/UPDpassword.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
>>>>>>> 1d960026149fd1b9709a702447cb97a865340c77
        }
    }
}
