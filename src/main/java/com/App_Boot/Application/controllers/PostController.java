package com.App_Boot.Application.controllers;
import com.App_Boot.Application.payload.PostDto;
import com.App_Boot.Application.payload.PostResponse;
import com.App_Boot.Application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId)
    {
        PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }
    //get by UserId
    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer userId){
        List<PostDto> post = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    //get by categoryId
    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId){
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    //getAllPosts
    @GetMapping("/allPosts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam (value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue ="postId",required = false)String sortBy
    ){
       PostResponse pop = this.postService.getAllPost(pageNumber,pageSize,sortBy);
        return new ResponseEntity<>(pop,HttpStatus.FOUND);
    }


    // getPostById
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
        PostDto get = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(get,HttpStatus.FOUND);
    }
    //Delete By Id
    @DeleteMapping("/post/{PostId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer PostId){
        this.postService.deletePost(PostId);
        return new ResponseEntity<>("post with postId: "+PostId+" is deleted successfully",HttpStatus.OK);
    }

    // update by PostId
    @PutMapping("update/{PostId}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable Integer PostId,
                                                  @RequestBody PostDto postDto){
          PostDto update= this.postService.updatePost(postDto,PostId);
          return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @GetMapping("posts/title/{keywords}")
 public ResponseEntity<List<PostDto>>searchPostByTitle(@PathVariable ("keywords") String keywords) {
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(result,HttpStatus.FOUND);
 }



}
