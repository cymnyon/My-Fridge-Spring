package com.myfridge.controller;

import com.myfridge.model.Category;
import com.myfridge.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public String viewCategory(@PathVariable Long categoryId, Model model) {
        // Fetch the category by ID using categoryService
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);

        // Pass the category and its notes to the template
        return "category"; // Return the category template
    }
}