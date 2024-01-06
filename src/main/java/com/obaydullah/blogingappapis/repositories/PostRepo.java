package com.obaydullah.blogingappapis.repositories;

import com.obaydullah.blogingappapis.entities.Category;
import com.obaydullah.blogingappapis.entities.Post;
import com.obaydullah.blogingappapis.payloads.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

   // List<PostRepo> findByUser(User user);
    Page<Post> findByCategory(Category category, Pageable pageable);

    Page<Post> findByUser(com.obaydullah.blogingappapis.entities.User user,Pageable pageable);


    /// this method for custom query search
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);

    List<Post> findByTitleContaining(String title);
}
