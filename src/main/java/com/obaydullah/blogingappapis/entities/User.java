package com.obaydullah.blogingappapis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity     /// @Entity(name="name") // if wants to change entity name
@Table(name = "users")     /// @Table(name="name") // if wants to change table name
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id     /// @Id to specify the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.AUTO)     /// for using auto increment of id
    private int id;

    @Column(name = "user_name" , nullable = false , length = 30)    /// change columns name change, nullable will make sure that datafield can be null or not , length will provide data length
    private String name;
    private String email;
    private String password;
    private String about;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Comment> comment = new HashSet<>();
}
