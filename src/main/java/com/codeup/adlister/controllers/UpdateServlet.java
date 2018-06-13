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

            if (username.isEmpty() || email.isEmpty() || passNew.isEmpty() || passCheck.isEmpty() || passOld.isEmpty()) {
                request.removeAttribute("errorMessage");
                List<String> errors = new ArrayList<>();
                errors.add("Please fill out all forms");
                request.setAttribute("errorMessage", errors);
                request.getRequestDispatcher("/WEB-INF/users/update.jsp").forward(request, response);

            }


            if (DaoFactory.getUsersDao().userExists(username)) {
                request.removeAttribute("erroMessage");
                List<String> errors = new ArrayList<>();
                errors.add("That username already exists. Please try again");
                request.setAttribute("errorMessage", errors);
                request.getRequestDispatcher("/WEB-INF/users/update.jsp").forward(request, response);

                return;
            }

            if (!Password.check(passOld, hash)) {
                request.removeAttribute("errorMessage");
                List<String> errors = new ArrayList<>();
                errors.add("The old password you entered was invalid. Please try again.");
                request.setAttribute("errorMessage", errors);
                request.getRequestDispatcher("/WEB-INF/users/update.jsp").forward(request, response);

                return;
            }

            if (!passNew.equals(passCheck)) {
                request.removeAttribute("errorMessage");
                List<String> errors = new ArrayList<>();
                errors.add("The new passwords entered did not match.");
                request.setAttribute("errorMessage", errors);
                request.getRequestDispatcher("/WEB-INF/users/update.jsp").forward(request, response);

                return;
            }

            String hashPW = Password.hash(passNew);
            DaoFactory.getUsersDao().updateUser(user, username, email, hashPW);
//        User newUser = DaoFactory.getUsersDao().findByUsername(username);
            User newUser = (User) DaoFactory.getSqlDao().findBySearch("users", "username", username);
            request.getSession().setAttribute("username", newUser.getUsername());
            response.sendRedirect("/profile");
        }
    }

