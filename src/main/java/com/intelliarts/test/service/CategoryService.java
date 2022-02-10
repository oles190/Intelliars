package com.intelliarts.test.service;


import com.intelliarts.test.dto.CategoryDTO;
import com.intelliarts.test.model.Category;
import com.intelliarts.test.model.SoldItems;

import java.time.LocalDate;
import java.util.List;

public interface CategoryService {

    CategoryDTO getById(Long id);

    CategoryDTO map(Category category);

    Category map(CategoryDTO categoryDTO);

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryByName(String name);

    CategoryDTO addItems(String name, int count);

    SoldItems purchase(String name, LocalDate date);

    List<CategoryDTO> getAll();

    String delete(String name);

    List<Category> test();


}




