package com.obaydullah.blogingappapis.repositories;

import com.obaydullah.blogingappapis.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
