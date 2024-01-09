package com.obaydullah.blogingappapis.controllers;


import com.obaydullah.blogingappapis.config.AppConstants;
import com.obaydullah.blogingappapis.payloads.ApiResponse;
import com.obaydullah.blogingappapis.payloads.PostDto;
import com.obaydullah.blogingappapis.payloads.PostResponse;
import com.obaydullah.blogingappapis.services.FileService;
import com.obaydullah.blogingappapis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    /// create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createProst(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ){
        PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }


    /// get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser(
         @PathVariable Integer userId,
         @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
         @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
         @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
         @RequestParam(value = "dir",defaultValue = AppConstants.SORT_DIR,required = false) String dir
    ){
        PostResponse posts = this.postService.getPostsByUser(userId,pageNumber,pageSize,sortBy,dir);
        return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
    }

    /// get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory(
            @PathVariable Integer categoryId,
             @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "dir",defaultValue = AppConstants.SORT_DIR,required = false) String dir
    ){
        PostResponse postResponse= this.postService.getPostsByCategory(categoryId,pageNumber,pageSize,sortBy,dir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    /// get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "dir",defaultValue = AppConstants.SORT_DIR,required = false) String dir
    ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,dir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    ///get post details by id

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(post,HttpStatus.OK);
    }

    /// update post
    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId){

        PostDto updatePost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);

    }

    /// delete post
    @DeleteMapping("posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){

        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted!!",true);
    }

    /// search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchByTitle(
            @PathVariable("keywords") String keywords
        ){
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }


    /// post image upload

    @PostMapping("post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {

        PostDto postDto = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadImage(path,image);

        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto,postId);
        return  new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);

    }

}
