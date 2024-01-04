package com.obaydullah.blogingappapis.payloads;

import com.obaydullah.blogingappapis.entities.Category;
import com.obaydullah.blogingappapis.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private int postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;

}
