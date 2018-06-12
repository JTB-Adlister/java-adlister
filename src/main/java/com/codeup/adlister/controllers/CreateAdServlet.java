package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.AdCategories;
//import com.codeup.adlister.dao.Categories;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.AdCategory;
import com.codeup.adlister.models.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        //List<Category> categories = DaoFactory.getCategoriesDao().listAll();

        List<Object> categories = DaoFactory.getSqlDao().listAll("categories", "category");
        request.getSession().setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/login");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String[] titles = request.getParameterValues("catSelect");

        if(title.isEmpty()||description.isEmpty()|| titles == null){
            request.getSession().setAttribute("errorMessage", null);
            List<String> errors = new ArrayList<>();
            errors.add("Please fill out all details");
            request.getSession().setAttribute("errorMessage", errors);
            response.sendRedirect("/ads/create");

        } else {
            System.out.println(request.getSession().getAttribute("userId"));
            long userid = (long) request.getSession().getAttribute("userId");


            //random long is generated in order to select ad and assign it category values
            long random = (long) (Math.random() * (10000 - 1)) + 1;


            Ad ad = new Ad(
                    userid,
                    random,
                    request.getParameter("title"),
                    request.getParameter("description")
            );


            DaoFactory.getAdsDao().insert(ad);


//            using getAdsDao.findByRandId instead of new method because long random can not be effectively cast to string
            Ad checkAd = (Ad) DaoFactory.getSqlDao().findBySearch("ads","randid",Long.toString(random));



            if(random == checkAd.getRandId() && userid == checkAd.getUserId()) {

                //creates a list of categories and assigns them to the ad
                List categories = Arrays.asList(titles);

                for (Object c : categories) {
                    int i = Integer.valueOf((String) c);

                    long id = (long) i;
                    AdCategory adCat = new AdCategory(checkAd.getId(), id);
                    DaoFactory.getAdCategoriesDao().insert(adCat);
                }


                response.sendRedirect("/ads");
            }
        }
    }
}
