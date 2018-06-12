package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.HelloWorldServlet", urlPatterns = "")
public class HelloWorldServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("ads", DaoFactory.getSqlDao().listAll("ads", "ad"));

        List<Object> categories = DaoFactory.getSqlDao().listAll("categories", "category");
        request.getSession().setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
