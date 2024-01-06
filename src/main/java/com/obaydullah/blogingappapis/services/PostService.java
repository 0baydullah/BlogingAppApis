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
    PostResponse getAllPost (Integer pageNumber , Integer pageSize,String sortBy,String dir);

    ///     get single post
    PostDto getPostById (Integer postId);

    ///     get all post by category
    PostResponse getPostsByCategory ( Integer categoryId,Integer pageNumber , Integer pageSize,String sortBy,String dir);

    ///     get all post by user
    PostResponse getPostsByUser ( Integer userId,Integer pageNumber, Integer pageSize,String sortBy,String dir);

    ///     search post
    List<PostDto> searchPosts ( String keyword);
}
