package com.App_Boot.Application.services;

import com.App_Boot.Application.entities.Category;
import com.App_Boot.Application.payload.CategoryDto;
import com.App_Boot.Application.payload.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    // Create Category
   Category create(Category category);

   //GetAll
   List<CategoryDto> getAllCategory();

   //GetById
    CategoryDto getCategoryById(int id);

    // Delete By Id

    void deleteById(int id);

    Category updateCategory( int id,Category category);
}
