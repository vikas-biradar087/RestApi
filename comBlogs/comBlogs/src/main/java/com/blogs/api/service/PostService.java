package com.blogs.api.service;

import com.blogs.api.payload.PostDto;
import com.blogs.api.payload.PostResponse;

import java.util.List;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto);

    //get All Post
    PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);

    //get data based on id
    public PostDto getPostById(long id);

    //update post based on id
     PostDto updatePost(PostDto postDto,long id);

     //delete post based on id
     void deletePostById(long id);



}
