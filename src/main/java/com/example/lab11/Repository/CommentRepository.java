package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Comment findByCommentId(Integer id);

    List<Comment> findAllByContent( String content );
}
