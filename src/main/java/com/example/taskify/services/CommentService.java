package com.example.taskify.services;

import com.example.taskify.entity.Comment;
import com.example.taskify.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> allComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public boolean deleteComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isPresent()) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Comment updateComment(Comment comment, Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isPresent()) {
            Comment comment1 = optionalComment.get();
            comment1.setContent(comment.getContent());
            comment1.setLocalDateTime(comment.getLocalDateTime());
            comment1.setTask(comment.getTask());
            return commentRepository.save(comment1);
        }
        return null;
    }
}
