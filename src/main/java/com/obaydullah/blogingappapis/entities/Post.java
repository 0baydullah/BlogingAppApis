package com.obaydullah.blogingappapis.entities;


import jakarta.persistence.*;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.Date;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title",length = 100, nullable = false)
    private String title;
    @Column(length = 10000)
    private String content;
    private String imageName;
    private Date addeddDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    private User user;


}
