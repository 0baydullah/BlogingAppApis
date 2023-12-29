package com.obaydullah.blogingappapis.payloads;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min = 3,message = "category name must be contain 3 characters")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10)
    private String categoryDescription;

}
