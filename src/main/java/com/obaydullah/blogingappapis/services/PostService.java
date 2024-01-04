package com.obaydullah.blogingappapis.services;

import com.obaydullah.blogingappapis.payloads.PostDto;
import com.obaydullah.blogingappapis.entities.Post;
import com.obaydullah.blogingappapis.payloads.PostResponse;

import java.util.List;

public interface PostService {
    ///     create
    PostDto createPost (PostDto postDto, Integer userId , Integer categoryId);

    ///     update
    PostDto updatePost (PostDto postDto,Integer postId);

    ///     delete
    void deletePost (Integer postId);

    ///     get all post
    PostResponse getAllPost (Integer pageNumber , Integer pageSize);

    ///     get single post
    PostDto getPostById (Integer postId);

    ///     get all post by category
    List<PostDto> getPostsByCategory ( Integer categoryId);

    ///     get all post by user
    List<PostDto> getPostsByUser ( Integer userId);

    ///     search post
    List<Post> searchPosts ( String keyword);



}
