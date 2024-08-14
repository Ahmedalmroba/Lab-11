package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Post;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    public void addPost(Post post) {
        if (userRepository.findUserById(post.getUserId())==null
                ||categoryRepository.findByCategoryId(post.getCategoryId())==null){
            throw new ApiException("post not found");
        }
        postRepository.save(post);
    }

    public void updatePost(Integer id ,Post post) {
        if (userRepository.findUserById(post.getUserId())==null
                ||categoryRepository.findByCategoryId(post.getCategoryId())==null) {
            throw new ApiException("post not found");
        }
        Post p = postRepository.findByPostId(id);
        if(p == null) {
            throw new ApiException("post not found");
        }

        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setPublishDate(post.getPublishDate());
        p.setCategoryId(post.getCategoryId());
        p.setUserId(post.getUserId());
        postRepository.save(p);
    }
    public void deletePost(Integer id) {
        Post p = postRepository.findByPostId(id);
        if(p == null) {
            throw new ApiException("post not found");
        }
        postRepository.delete(p);


    }
    public List<Post> getPostsByUserID(Integer userID) {
        List<Post> posts = postRepository.findByUserId(userID);
        if (posts.isEmpty()) {
            throw new ApiException("post not found");
        }
        return posts;
    }
    public List<Post> getPostsByTitle(String title) {
        List<Post> posts = postRepository.findByTitle(title);
        if (posts.isEmpty()) {
            throw new ApiException("post not found");
        }
        return posts;
    }
    public List<Post> gfindByPublishDateBefore( LocalDate publishDate) {
        List<Post> posts = postRepository.findByPublishDateBefore(publishDate);
        if (posts.isEmpty()) {
            throw new ApiException("post not found");
        }
        return posts;
    }
}
