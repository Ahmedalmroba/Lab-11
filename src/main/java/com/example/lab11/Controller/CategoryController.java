package com.example.lab11.Controller;

import com.example.lab11.Model.Category;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory() {
        return ResponseEntity.status(200).body(categoryService.getAllCategory());

    }

    @PutMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("is added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        categoryService.updateCategory(id, category);
        return ResponseEntity.status(200).body("is updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body("is deleted");
    }
    @GetMapping("/get/{name}")
    public ResponseEntity findCategory(@RequestBody String name) {
        List <Category> categories = categoryService.getCategoryByCategoryName(name);
        return ResponseEntity.status(200).body(categories);

    }

}

