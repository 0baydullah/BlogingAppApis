package com.obaydullah.blogingappapis.services;

import com.obaydullah.blogingappapis.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
    void deleteCommnet(Integer commentId);
}
