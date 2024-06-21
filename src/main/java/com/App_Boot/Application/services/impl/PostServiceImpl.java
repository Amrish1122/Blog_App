package com.App_Boot.Application.services.impl;

import com.App_Boot.Application.entities.Category;
import com.App_Boot.Application.entities.Post;
import com.App_Boot.Application.entities.User;
import com.App_Boot.Application.exception.ResourceNotFoundException;
import com.App_Boot.Application.payload.PostDto;
import com.App_Boot.Application.payload.PostResponse;
import com.App_Boot.Application.reopsitory.CategoryRepo;
import com.App_Boot.Application.reopsitory.PostRepo;
import com.App_Boot.Application.reopsitory.UserRepo;
import com.App_Boot.Application.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;
    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {
       /* User user = userRepo.findById(userId).orElseThrow(()->

                new ResourceNotFoundException("user","userId",userId));

        Category category = categoryRepo.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("category","categoryId",categoryId));
         post.setImageName("Default Png");
         post.setAddedDate(new Date());
         post.setUser(user);
         post.setCategory(category);

        Post post1 = postRepo.save(post);

        return post1;
        */
       User user=this.userRepo.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User","UserId",userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("category","categoryId",categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("Default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);


    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post= this.postRepo.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("post","postId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
       Post updatedPost = this.postRepo.save(post);
       PostDto updated = this.modelMapper.map(updatedPost,PostDto.class);
        return updated;
    }

    @Override
    public void deletePost(Integer postId) {
       Post delete = this.postRepo.findById(postId).orElseThrow(()->
               new ResourceNotFoundException("post","PostId",postId));
        this.postRepo.delete(delete);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable p = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy).descending());
        Page<Post> pagePost = this.postRepo.findAll(p);

        List<Post> postList = pagePost.getContent();
        List<PostDto> dtos = postList.stream().map((list)->
                this.modelMapper.map(list,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(dtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post getPost = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
        PostDto dto = this.modelMapper.map(getPost,PostDto.class);

        return dto;
    }

    @Override
    public List<PostDto> getPostByCategory(int id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("category","categoryId",id));
        List<Post> posts = this.postRepo.findByCategory(cat);
       List<PostDto> postDtos = posts.stream().map((post) ->
               this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(int id) {
        User user = this.userRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("user","userId",id));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> dto = posts.stream().map((post )->
                this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return dto;
    }

    @Override
    public List<PostDto> searchPosts(String Keywords) {

        List <Post> posts = this.postRepo.findByTitleContaining(Keywords);
        List<PostDto> dto = posts.stream().map((post1) -> this.modelMapper.map(post1,PostDto.class) ).collect(Collectors.toList());
        return dto;
    }
}
