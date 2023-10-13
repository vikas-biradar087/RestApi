package com.blogs.api.Controller;

import com.blogs.api.payload.CommentDto;
import com.blogs.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){

        CommentDto comments = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comments, HttpStatus.CREATED);
    }


    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getCommentById(@PathVariable("postId") long postId){
        List<CommentDto> d = commentService.getCommentsByPostId(postId);
        return d;


    }


    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updatedComment(@PathVariable("postId") long postId, @PathVariable("id") long id,
            @RequestBody CommentDto commentDto){

        CommentDto dto = commentService.updateComment(postId, id, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,@PathVariable("id") long id){
        commentService.deleteComment(postId,id);
        return new ResponseEntity<>("Comment is deleted successfully !!",HttpStatus.OK);
    }


}
