package com.blogs.api.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;

    @Size(min = 2,message = "Post title should have at least 2 characters")
    private String title;
    @Size(min = 4,message = "Post description should have at least 10 characters")
    @NotEmpty
    private String description;

    @NotEmpty(message = "Content most not be empty")
    private String content;
}


