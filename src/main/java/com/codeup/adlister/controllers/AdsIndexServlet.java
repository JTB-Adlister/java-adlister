package com.codeup.adlister.controllers;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ads", DaoFactory.getSqlDao().listAll("ads", "ad"));
        //List<Category> categories = DaoFactory.getCategoriesDao().listAll();
        List<Object> categories = DaoFactory.getSqlDao().listAll("categories", "category");


        ArrayList allCategories = new ArrayList<>();


        for(Object category: categories) {
            Category newCat = (Category) category;
            String title = newCat.getCatTitle();
            List<Ad> adListByCat = DaoFactory.getAdsDao().listByCat( (int) newCat.getId());

            List<Object> categoryList = new ArrayList<>();
            categoryList.add(title);
            categoryList.add(adListByCat);
            allCategories.add(categoryList);
        }

        List<Object> ads = DaoFactory.getSqlDao().listAll("ads", "ad");
        Ad randomOne = DaoFactory.getAdsDao().randomAd(ads);
        Ad randomTwo = DaoFactory.getAdsDao().randomAd(ads);
        Ad randomThree = DaoFactory.getAdsDao().randomAd(ads);

        List<Ad> ranList = new ArrayList<>();
        ranList.add(randomOne);
        ranList.add(randomTwo);
        ranList.add(randomThree);

        request.getSession().setAttribute("ranList", ranList);

        for(Object item: allCategories){
            System.out.println(item);

        }

        request.getSession().setAttribute("adsByCat", allCategories);

        request.getSession().setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("viewUser");
//        User user = DaoFactory.getUsersDao().findByUserId(userId);
        User user = (User) DaoFactory.getSqlDao().findBySearch("users", "id", userId);
        long id = user.getId();
        request.setAttribute("viewUser", user);
        request.setAttribute("viewUserId", id);
        int userid = (int) id;
//        List<Ad> userAds = DaoFactory.getAdsDao().listByUser(userid);
        List<Object> userAds = DaoFactory.getSqlDao().listBySearch("ads", "userid", userId);
        request.setAttribute("viewUserAds", userAds);
        request.setAttribute("viewUserName", user.getUsername());
        request.getRequestDispatcher("/WEB-INF/viewProfile.jsp").forward(request, response);
    }
}