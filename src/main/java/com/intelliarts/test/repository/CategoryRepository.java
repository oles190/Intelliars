package com.intelliarts.test.repository;

import com.intelliarts.test.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getCategoryByName(String name);

}