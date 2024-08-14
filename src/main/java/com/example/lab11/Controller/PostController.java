package com.example.lab11.Controller;

import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;
    @GetMapping("/get")
    public ResponseEntity getPost(){
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }
    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors errors){
        if(errors.hasErrors()){
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        postService.addPost(post);
        return ResponseEntity.status(201).body("is added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id,@Valid @RequestBody Post post, Errors errors){
        if(errors.hasErrors()){
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);

        }
        postService.updatePost(id, post);
        return ResponseEntity.status(200).body("is updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.status(200).body("is deleted");

    }
    @GetMapping("/get/{userID}")
    public ResponseEntity getPostByUserID(@PathVariable Integer userID){
        List<Post> posts = postService.getPostsByUserID(userID);
       return ResponseEntity.status(200).body("posts found");
    }
    @GetMapping("/get/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title){
        List<Post> posts = postService.getPostsByTitle(title);
        return ResponseEntity.status(200).body("posts found");
    }
    @GetMapping("/get/{publishDate}")
    public ResponseEntity getByDate(@RequestBody LocalDate publishDate){
        List<Post> posts = postService.gfindByPublishDateBefore(publishDate);
        return ResponseEntity.status(200).body("posts found");
    }


}
