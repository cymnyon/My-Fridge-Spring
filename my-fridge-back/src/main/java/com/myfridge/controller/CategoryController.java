package com.myfridge.controller;

import com.myfridge.model.Category;
import com.myfridge.model.User;
import com.myfridge.service.CategoryService;
import com.myfridge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api")
public class CategoryController {
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    @ResponseBody
    public ResponseEntity<String> addCategory(@RequestBody Map<String, String> request) {
        User currentUser = userService.getCurrentUser();
        String title = request.get("title");
        if (currentUser != null) {
            Category newCategory = categoryService.createCategory(currentUser, title);
            if (newCategory != null) {
                return ResponseEntity.ok("Category added successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add category");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
    }

    @PutMapping("/categories/{id}")
    @ResponseBody
    public ResponseEntity<String> editCategory(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String newTitle = request.get("title");
        Category existingCategory = categoryService.getCategoryById(id);
        if (existingCategory != null) {
            existingCategory.setName(newTitle);
            categoryService.updateCategory(existingCategory);
            return ResponseEntity.ok("Category updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }

    @DeleteMapping("/categories/{id}")
    @ResponseBody
    public ResponseEntity<String> removeCategory(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            categoryService.removeCategory(category);
            return ResponseEntity.ok("Category removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }
}