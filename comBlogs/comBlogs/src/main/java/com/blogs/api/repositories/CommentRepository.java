package com.blogs.api.repositories;

import com.blogs.api.entity.Comment;
import com.blogs.api.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postId);

}
