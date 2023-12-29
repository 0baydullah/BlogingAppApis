package com.obaydullah.blogingappapis.repositories;

import com.obaydullah.blogingappapis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
