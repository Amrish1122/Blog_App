package com.App_Boot.Application.controllers;

import com.App_Boot.Application.entities.Category;
import com.App_Boot.Application.payload.CategoryDto;
import com.App_Boot.Application.payload.UserDto;
import com.App_Boot.Application.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category){
        Category add = this.categoryService.create(category);
        return new ResponseEntity<>(add,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategory(){
       List< CategoryDto> dto=this.categoryService.getAllCategory();
       return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> CategoryById(@PathVariable int id){
        CategoryDto maa = this.categoryService.getCategoryById(id);
        return new ResponseEntity<>( maa,HttpStatus.FOUND);

    }
    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        categoryService.deleteById(id);
        return new ResponseEntity<>("category with id "+id+" deleted successfully",HttpStatus.OK);
    }
    @PutMapping("/{id}")

    public ResponseEntity<Category> updateById(@Valid @PathVariable int id, @RequestBody Category category){
        categoryService.updateCategory(id, category);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }


}
