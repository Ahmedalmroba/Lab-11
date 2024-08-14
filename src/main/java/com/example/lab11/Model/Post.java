package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer categoryId;
    @NotEmpty(message = " should be not Empty")
    @Column(columnDefinition = "varchar(20) not null  ")
    private String title;
    @NotEmpty(message = " should be not Empty")
    @Column(columnDefinition = "varchar(30) not null  ")
    private String content;
    private Integer userId;

    private LocalDate publishDate;


}
