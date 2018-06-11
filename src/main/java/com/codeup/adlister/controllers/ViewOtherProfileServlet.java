package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.ViewOtherProfileServlet", urlPatterns = "/viewuser")
public class ViewOtherProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/viewProfile.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("userInfo");
        User user = DaoFactory.getUsersDao().findByUsername(name);
        int userid = (int) user.getId();
        List<Ad> userAds = DaoFactory.getAdsDao().listByUser(userid);
        System.out.println(request.getSession().getAttribute("role").toString());
        request.setAttribute("viewUserAds", userAds);
        request.setAttribute("viewUserName", user.getUsername());
        request.setAttribute("viewUserId", user.getId());
        request.getRequestDispatcher("/WEB-INF/viewProfile.jsp").forward(request, response);

    }
}
