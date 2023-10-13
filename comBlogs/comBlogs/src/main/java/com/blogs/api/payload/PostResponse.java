package com.blogs.api.payload;

import lombok.Data;

import java.util.List;
@Data
public class PostResponse {

    List<PostDto> content;

    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}
