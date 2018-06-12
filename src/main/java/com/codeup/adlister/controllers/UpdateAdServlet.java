package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.AdCategories;
import com.codeup.adlister.dao.Categories;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.AdCategory;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "controllers.UpDateAdServlet", urlPatterns = "/update")
public class UpdateAdServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;

        }
        long userId = (long) request.getSession().getAttribute("userId");
//        int adId = Integer.parseInt(request.getParameter("adInfo"));
//        Ad newAd = DaoFactory.getAdsDao().findById(adId);
        Ad newAd = (Ad) DaoFactory.getSqlDao().findBySearch("ads", "id", request.getParameter("adInfo"));
        if(userId == newAd.getUserId()) {
            String title = newAd.getTitle();
            String description = newAd.getDescription();
            request.setAttribute("title", title);
            request.setAttribute("description", description);
            DaoFactory.getAdsDao().deleteQuery(request.getParameter("adInfo"));
            request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
        } else {
            response.sendRedirect("/ads");
        }
    }
}
