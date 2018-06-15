package com.codeup.adlister.controllers;

//import com.codeup.adlister.dao.Categories;
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

        //request categoryList from navbar category selection that provides category id value
        String catId = request.getParameter("categoryList");

        //List<Category> categories = DaoFactory.getCategoriesDao().listById(id);
        //populates an object list of categories SELECT * FROM categories WHERE id = catId
        List<Object> categories = DaoFactory.getSqlDao().listBySearch("categories", "id", catId);
        request.getSession().setAttribute("catType", categories);

        request.getRequestDispatcher("/WEB-INF/ads/catindex.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String catId = request.getParameter("categoryList");
        //catId is parsed to int id in order to list ads by category id
        int id = Integer.parseInt(catId);

        //List<Category> categories = DaoFactory.getCategoriesDao().listById(id);
        List<Object> categoryList = DaoFactory.getSqlDao().listBySearch("categories", "id", catId);
        Category category = (Category) categoryList.get(0);

        request.setAttribute("catTitle", category.getCatTitle());
        List<Ad> ads = DaoFactory.getAdsDao().listByCat(id);
        for(Ad ad: ads){
            System.out.println("title " + ad.getTitle());
            System.out.println("description " + ad.getDescription());
            System.out.println("catTitle " + ad.getCatTitle());
            System.out.println("id" + ad.getId());
        }
        request.getSession().setAttribute("ads", ads);
        request.getRequestDispatcher("/WEB-INF/ads/catindex.jsp").forward(request,response);
    }
}

