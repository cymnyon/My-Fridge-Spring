package com.myfridge.service;

import com.myfridge.model.Category;
import com.myfridge.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class CategoryService {

    private CategoryRepository categoryRepository = null;

    @Autowired
    public CategoryService() {
        this.categoryRepository = categoryRepository;
    }

    public abstract Category getCategoryById(Long categoryId);

    public List<Category> getCategoriesByUserId(Long userId) {
        return categoryRepository.findByUserId(userId);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Add more methods as needed for category-related operations

}