package com.obaydullah.blogingappapis.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

     private List<PostDto> content;
     private int pageNumber;
     private int pageSize;
     private int totalElement;
     private int totalPages;
     private boolean lastPage;

}
