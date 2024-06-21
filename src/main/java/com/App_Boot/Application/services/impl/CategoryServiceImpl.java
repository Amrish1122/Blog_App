package com.App_Boot.Application.services.impl;

import com.App_Boot.Application.entities.Category;
import com.App_Boot.Application.exception.ResourceNotFoundException;
import com.App_Boot.Application.payload.CategoryDto;
import com.App_Boot.Application.payload.UserDto;
import com.App_Boot.Application.reopsitory.CategoryRepo;
import com.App_Boot.Application.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories = new ArrayList<>();
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;


   // private ModelMapper modelMapper;



    @Override
    public Category create( Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> getAll =this.categoryRepo.findAll();
      List <CategoryDto> categoryDto=getAll.stream().map((category)->
              this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDto;
    }

    @Override
    public CategoryDto getCategoryById(int categoryId) {
        Category get = this.categoryRepo.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("category","categoryId",categoryId));
        return this.modelMapper.map(get,CategoryDto.class) ;
    }

    @Override
    public void deleteById(int categoryId) {
       Category Deleted=categoryRepo.findById(categoryId).orElseThrow(()->new
               ResourceNotFoundException("category","id",categoryId));
            categoryRepo.delete( Deleted);
       }

    @Override
    public Category updateCategory(int categoryId, Category category) {
        Category updated = categoryRepo.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        updated.getId();
       updated.setTitle(category.getTitle());
        updated.setDescription(category.getDescription());
        updated=categoryRepo.save(updated);
        return updated;
    }

    /*
   // @Override
    //public Category create(Category category) {
     //   return categoryRepo.save(category);
      // Category cat = this.modelMapper.map(categoryDto,Category.class);
       //Category addedCat= this.categoryRepo.save(cat);
       // Category category= this.modelMapper.map(categoryDto, Category.class);
      //Category savedCat= this.categoryRepo.save(category);
       // return this.modelMapper.map(addedCat,CategoryDto.class);
    //}


    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Integer CategoryID) {
        Category cat = this.categoryRepo.findById(CategoryID).orElseThrow(()-> new
                ResourceNotFoundException("Category","CategoryId",CategoryID));
        cat.setTitle(categoryDto.getTitle());
        cat.setAbout(categoryDto.getAbout());
        Category Updated= this.categoryRepo.save(cat);

        return this.modelMapper.map(Updated,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer CategoryId) {
        Category find = this.categoryRepo.findById(CategoryId).orElseThrow(()->
                new ResourceNotFoundException("Category","CategoryId",CategoryId));
        this.categoryRepo.delete(find);

    }

    @Override
    public CategoryDto getCategory(Integer CategoryId) {
        Category find= this.categoryRepo.findById(CategoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",CategoryId));


        return this.modelMapper.map(find,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList=this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos =categoryList.stream().map((category) ->this.modelMapper.map(category,CategoryDto.class) ).collect(Collectors.toList());
        return categoryDtos;
    }

     */
}
