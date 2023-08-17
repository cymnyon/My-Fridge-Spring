package com.myfridge.service.impl;

import com.myfridge.model.Category;
import com.myfridge.model.User;
import com.myfridge.repository.CategoryRepository;
import com.myfridge.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public List<Category> getCategoriesForUser(User user) {
        return categoryRepository.findByUser(user);
    }

    public Category createCategory(User user, String title) {
        Category category = new Category();
        category.setUser(user);
        category.setName(title);
        return categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Category category) {
        categoryRepository.delete(category);
    }
}