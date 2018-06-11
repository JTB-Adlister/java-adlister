package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.Categories;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.CatIndexServlet", urlPatterns = "/ad_categories")
public class CatIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("catType") == null) {
            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
        }
        String catId = request.getParameter("categoryList");
        String catList = request.getParameter("catForm");
        System.out.println(catList);
        System.out.println(catId);
        int id = Integer.parseInt(catId);

        List<Category> categories = DaoFactory.getCategoriesDao().listById(id);
        request.getSession().setAttribute("catType", categories);
        request.getRequestDispatcher("/WEB-INF/ads/catindex.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String catId = request.getParameter("categoryList");

        System.out.println(catId);
        int id = Integer.parseInt(catId);
        List<Category> categories = DaoFactory.getCategoriesDao().listById(id);
        String catTitle = categories.get(0).getCatTitle();

        request.setAttribute("catTitle", catTitle);
        List<Ad> ads = DaoFactory.getAdsDao().listByCat(id);
        System.out.println(ads.size());
        request.getSession().setAttribute("ads", ads);
        request.getRequestDispatcher("/WEB-INF/ads/catindex.jsp").forward(request,response);
    }
}
