package com.example.lab11.Repository;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findByPostId(Integer postId);

    @Query("select p from Post p where p.id=?1 ")
    List<Post> findByUserId(Integer userId);

    List<Post> findByTitle(String title);

    List<Post> findByPublishDateBefore(LocalDate publishDate);
}
