package com.obaydullah.blogingappapis.services.impl;

import com.obaydullah.blogingappapis.entities.Category;
import com.obaydullah.blogingappapis.entities.Post;
import com.obaydullah.blogingappapis.entities.User;
import com.obaydullah.blogingappapis.exceptions.ResourceNotFoundException;
import com.obaydullah.blogingappapis.payloads.PostDto;
import com.obaydullah.blogingappapis.payloads.PostResponse;
import com.obaydullah.blogingappapis.repositories.CategoryRepo;
import com.obaydullah.blogingappapis.repositories.PostRepo;
import com.obaydullah.blogingappapis.repositories.UserRepo;
import com.obaydullah.blogingappapis.services.PostService;
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
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
        this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber , Integer pageSize,String sortBy,String dir) {

        Sort sort = null;
        if(dir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else if(dir.equalsIgnoreCase("dsc")) {
            sort = Sort.by(sortBy).descending();
        }else  sort = Sort.by(sortBy).ascending();

        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream().map((post -> this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement((int)pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {

       Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post " , "post ID " , postId));
       return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId,Integer pageNumber , Integer pageSize,String sortBy,String dir) {

        Sort sort = null;
        if(dir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else if(dir.equalsIgnoreCase("dsc")) {
            sort = Sort.by(sortBy).descending();
        }else  sort = Sort.by(sortBy).ascending();

        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ","Category id ", categoryId));
        Page<Post> pagePost = this.postRepo.findByCategory(cat,p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto>postDtos = allPosts.stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement((int)pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId,Integer pageNumber , Integer pageSize,String sortBy,String dir) {

        Sort sort = null;
        if(dir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else if(dir.equalsIgnoreCase("dsc")) {
            sort = Sort.by(sortBy).descending();
        }else  sort = Sort.by(sortBy).ascending();

        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user ","user id ", userId));
        Page<Post> posts = this.postRepo.findByUser(user,p);
        List<Post> allPosts = posts.getContent();
        List<PostDto>postDtos = allPosts.stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElement((int)posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
