package com.blogs.api.serviceImpl;

import com.blogs.api.entity.Comment;
import com.blogs.api.entity.Post;
import com.blogs.api.exception.ResourceNotFoundException;
import com.blogs.api.payload.CommentDto;
import com.blogs.api.repositories.CommentRepository;
import com.blogs.api.repositories.PostRepository;
import com.blogs.api.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceimpl implements CommentService {
    
    private CommentRepository commentRepository;
    
    private PostRepository postRepository;
    
    public CommentServiceimpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = mapToEntity(commentDto);
        // set post to comment entity
            comment.setPost(post);

        Comment com = commentRepository.save(comment);
        return mapToDTO(com);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> commentss = commentRepository.findByPostId(postId);
        return commentss.stream().map(x->mapToDTO(x)).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment upadtedComment = commentRepository.save(comment);

        return mapToDTO(upadtedComment);
    }

    @Override
    public void deleteComment(long postId,long id) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        Comment deleteComment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", id));

        commentRepository.deleteById(id);
    }

    // convert Entity into DTO
    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto=new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());

        return commentDto;

    }

    // convert DTO to entity
    private Comment mapToEntity(CommentDto commentDto){

        Comment comment=new Comment();
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
