package com.obaydullah.blogingappapis.repositories;

import com.obaydullah.blogingappapis.entities.Category;
import com.obaydullah.blogingappapis.entities.Post;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<PostRepo> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByUser(com.obaydullah.blogingappapis.entities.User user);
}
