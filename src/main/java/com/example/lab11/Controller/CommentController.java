package com.example.lab11.Controller;

import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/get")
    public ResponseEntity GetComment() {
        return ResponseEntity.status(200).body(commentService.getAllComment());

    }
    @PutMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body("added comment successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id,@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body("updated comment successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body("deleted comment successfully");
    }
    @GetMapping("/get/{publishDate}")
    public ResponseEntity getByContent (@RequestBody String content){
        List<Comment> comments = commentService.getByContent(content);
        return ResponseEntity.status(200).body(comments);

    }
}
