package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;
import java.util.List;

public interface Categories {
    List<Category> listById (int id);
    List<Category> listAll();
}
