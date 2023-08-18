package com.myfridge.repository;

import com.myfridge.model.Category;
import com.myfridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserId(Long userId);
    List<Category> findByUser(User user);
    Optional<Category> findById(Long categoryId);
}