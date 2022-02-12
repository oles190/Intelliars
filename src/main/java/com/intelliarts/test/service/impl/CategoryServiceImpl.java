package com.intelliarts.test.service.impl;


import com.intelliarts.test.dto.CategoryDTO;
import com.intelliarts.test.exception.category.CategoryItemsException;
import com.intelliarts.test.model.Category;
import com.intelliarts.test.model.SoldItems;
import com.intelliarts.test.repository.CategoryRepository;
import com.intelliarts.test.service.CategoryService;
import com.intelliarts.test.service.SoldItemsService;
import com.intelliarts.test.validator.CategoryCreateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SoldItemsService soldItemsService;
    private final List<CategoryCreateValidator> categoryCreateValidator;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, SoldItemsService soldItemsService, List<CategoryCreateValidator> categoryCreateValidator) {
        this.categoryRepository = categoryRepository;
        this.soldItemsService = soldItemsService;
        this.categoryCreateValidator = categoryCreateValidator;
    }



    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        for (CategoryCreateValidator validator : categoryCreateValidator) {
            validator.validate(map(categoryDTO));
        }
        Category category = map(categoryDTO);
        categoryRepository.save(category);
        return map(category);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return map(categoryRepository.getCategoryByName(name));
    }

    public CategoryDTO addItems(String name, int count) {
        CategoryDTO category = getCategoryByName(name);
        if (count <= 0) {
            throw new CategoryItemsException("Items can't be 0 or less than 0!");
        }
        category.setItems(category.getItems() + count);
        categoryRepository.save(map(category));
        return category;
    }

    @Override
    public CategoryDTO map(Category category) {
        return new CategoryDTO(category);
    }

    @Override
    public Category map(CategoryDTO categoryDTO) {
        return new Category(categoryDTO);
    }

    public SoldItems purchase(String name, LocalDate date) {
        Category category = categoryRepository.getCategoryByName(name);
        if (category.getItems() == 0) {
            throw new CategoryItemsException("Items is 0");
        }
        category.setItems(category.getItems() - 1);
        categoryRepository.save(category);
        SoldItems purchase = new SoldItems(category.getName(), category.getPrice(), date, 1);
        soldItemsService.create(purchase);
        return purchase;
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::map).sorted().collect(Collectors.toList());
    }

    public String delete(String name) {
        Category category = categoryRepository.getCategoryByName(name);
        categoryRepository.delete(category);
        return category.getName() + " " + category.getPrice();

    }


}
