package com.blogs.api.service;

import com.blogs.api.payload.CommentDto;

import java.util.List;

public interface CommentService {


    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto updateComment(long postId,long id,CommentDto commentDto);

    void deleteComment(long postId,long id);
}
