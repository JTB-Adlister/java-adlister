package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.ShowInfoServlet", urlPatterns = "/showinfo")
public class ShowAdInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("showAd") == null) {
            response.sendRedirect("/ads");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/adInfo.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adid = request.getParameter("adInfo");

        //Ad showAd = DaoFactory.getAdsDao().findById(adId);
        Ad showAd = (Ad) DaoFactory.getSqlDao().findBySearch("ads", "id", adid);

        long userId = showAd.getUserId();
        request.setAttribute("showAd", showAd);
        request.getSession().setAttribute("adUserId", userId);
        request.getRequestDispatcher("/WEB-INF/ads/adInfo.jsp").forward(request, response);

    }
}