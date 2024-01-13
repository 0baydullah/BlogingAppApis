package com.obaydullah.blogingappapis.controllers;


import com.obaydullah.blogingappapis.payloads.ApiResponse;
import com.obaydullah.blogingappapis.payloads.CommentDto;
import com.obaydullah.blogingappapis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId,@PathVariable Integer userId){
        CommentDto createComment = this.commentService.createComment(comment, postId,userId);
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping ("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteCommnet(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment delete successfully!!",true), HttpStatus.OK);
    }
}
