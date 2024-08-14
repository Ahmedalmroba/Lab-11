package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Comment;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public List<Comment> getAllComment(){
        return (List<Comment>) commentRepository.findAll();
    }
    public void addComment(Comment comment){
        if (userRepository.findUserById(comment.getUserId())==null||
                postRepository.findByPostId(comment.getPostId())==null){
            throw new ApiException("User not found");
        }

        commentRepository.save(comment);

    }
    public void updateComment(Integer id, Comment comment){
        if (userRepository.findUserById(comment.getUserId())==null||
                postRepository.findByPostId(comment.getPostId())==null) {
            throw new ApiException("User not found");
        }
        Comment c = commentRepository.findByCommentId(id);
        if(c == null){
            throw new ApiException("Comment not found");
        }
        c.setUserId(comment.getUserId());
        c.setContent(comment.getContent());
        c.setPostId(comment.getPostId());
        c.setCommentDate(comment.getCommentDate());
        commentRepository.save(c);
    }
    public void deleteComment(Integer id){
        Comment c = commentRepository.findByCommentId(id);
        if(c == null){
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(c);
    }
    public List<Comment> getByContent(String content){
        List<Comment> c = commentRepository.findAllByContent(content);
        if(c==null){
            throw new ApiException("Comment not found");
        }
        return c;
    }
}

