package com.obaydullah.blogingappapis.payloads;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int commentId;
    private String content;
    private UserDto user;
}
