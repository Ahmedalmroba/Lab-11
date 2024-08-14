package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();

    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public void updateCategory(Integer id,Category category){
        Category c = categoryRepository.findByCategoryId(id);
        if(c == null){
            throw new ApiException("Category not found");
        }
        c.setName(category.getName());
        categoryRepository.save(c);
    }
    public void deleteCategory(Integer id){
        Category c = categoryRepository.findByCategoryId(id);
        if(c == null){
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(c);
    }
    public List<Category> getCategoryByCategoryName(String categoryName){
        List<Category> categories = categoryRepository.findByCategoryName(categoryName);
        if(categories == null ){
            throw new ApiException("Category not found");
        }
        return categories;
    }
}
