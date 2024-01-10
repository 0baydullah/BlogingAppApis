package com.obaydullah.blogingappapis.services.impl;

import com.obaydullah.blogingappapis.entities.Comment;
import com.obaydullah.blogingappapis.entities.Post;
import com.obaydullah.blogingappapis.exceptions.ResourceNotFoundException;
import com.obaydullah.blogingappapis.payloads.CommentDto;
import com.obaydullah.blogingappapis.repositories.CommentRepo;
import com.obaydullah.blogingappapis.repositories.PostRepo;
import com.obaydullah.blogingappapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);


        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteCommnet(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment " ,"comment id",commentId));
        this.commentRepo.delete(com);
    }
}
