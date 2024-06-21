package com.App_Boot.Application.services;

import com.App_Boot.Application.entities.Post;
import com.App_Boot.Application.payload.PostDto;
import com.App_Boot.Application.payload.PostResponse;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost( PostDto postDto,int userId,int categoryId);

    //update

    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAll Post

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy);
    //Get single Post

    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostByCategory(int id);

    // get all post by user

    List<PostDto> getPostByUser(int id);

    //search posts
    List<PostDto> searchPosts(String Keywords);

}
