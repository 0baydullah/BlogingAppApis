package com.obaydullah.blogingappapis.services.impl;

import com.obaydullah.blogingappapis.entities.Category;
import com.obaydullah.blogingappapis.entities.Post;
import com.obaydullah.blogingappapis.entities.User;
import com.obaydullah.blogingappapis.exceptions.ResourceNotFoundException;
import com.obaydullah.blogingappapis.payloads.PostDto;
import com.obaydullah.blogingappapis.repositories.CategoryRepo;
import com.obaydullah.blogingappapis.repositories.PostRepo;
import com.obaydullah.blogingappapis.repositories.UserRepo;
import com.obaydullah.blogingappapis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("Name ","User Id",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category id",categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);

    }

    @Override
    public Post updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
