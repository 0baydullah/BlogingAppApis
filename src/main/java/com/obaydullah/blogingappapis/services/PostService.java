package com.obaydullah.blogingappapis.services;

import com.obaydullah.blogingappapis.payloads.PostDto;
import com.obaydullah.blogingappapis.entities.Post;

import java.util.List;

public interface PostService {
    ///     create
    PostDto createPost (PostDto postDto, Integer userId , Integer categoryId);

    ///     update
    Post updatePost (PostDto postDto);

    ///     delete
    void deletePost (Integer postId);

    ///     get all post
    List<Post> getAllPost ();

    ///     get single post
    Post getPostById (Integer postId);

    ///     get all post by category
    List<PostDto> getPostsByCategory ( Integer categoryId);

    ///     get all post by user
    List<PostDto> getPostsByUser ( Integer userId);

    ///     search post
    List<Post> searchPosts ( String keyword);



}
