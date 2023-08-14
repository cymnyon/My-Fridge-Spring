package com.myfridge.service;

import com.myfridge.model.Category;
import com.myfridge.model.User;
import com.myfridge.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public abstract Category getCategoryById(Long categoryId);

    public List<Category> getCategoriesByUserId(Long userId) {
        return categoryRepository.findByUserId(userId);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public abstract List<Category> getCategoriesForUser(User user);
}