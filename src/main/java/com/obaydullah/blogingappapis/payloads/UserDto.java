package com.obaydullah.blogingappapis.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;


    @NotEmpty
    @Size(min=4, message = "Name must be 4 letter long")
    private String name;


    @Email(message = "Email address is not valid")
    private String email;



    @NotEmpty
    @Size(min = 4, max = 16 , message = "Password should be long 4 to 16 characters")
    private String password;



    @NotEmpty
    private String about;
}
