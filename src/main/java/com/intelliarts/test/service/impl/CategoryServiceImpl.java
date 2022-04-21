package com.intelliarts.test.service.impl;

import com.intelliarts.test.dto.CategoryDTO;
import com.intelliarts.test.exception.category.CategoryItemsException;
import com.intelliarts.test.model.Category;
import com.intelliarts.test.model.SoldItems;
import com.intelliarts.test.repository.CategoryRepository;
import com.intelliarts.test.service.CategoryService;
import com.intelliarts.test.service.SoldItemsService;
import com.intelliarts.test.validator.CategoryCreateValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SoldItemsService soldItemsService;
    private final List<CategoryCreateValidator> categoryCreateValidator;

    @Override
    public CategoryDTO getCategory(String name) {
        Category category = categoryRepository.getCategoryByName(name);
        if (category == null) {
            throw new CategoryItemsException("Category is null");
        }
        return map(category);
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        for (CategoryCreateValidator validator : categoryCreateValidator) {
            validator.validate(map(categoryDTO));
        }
        Category isPresent = categoryRepository.getCategoryByName(categoryDTO.getName());
        if (isPresent != null) {
            throw new RuntimeException("Already Exist");
        }
        Category category = map(categoryDTO);
        categoryRepository.save(category);
        return map(category);
    }

    @Override
    public CategoryDTO addItems(String name, int count) {
        if (count <= 0) {
            throw new RuntimeException("Count can't be  0 or less than 0!");
        }
        CategoryDTO categoryDTO = getCategory(name);
        validateItems(map(categoryDTO));
        categoryDTO.setItems(categoryDTO.getItems() + count);
        categoryRepository.save(map(categoryDTO));
        log.info("Success!");
        return categoryDTO;
    }

    @Override
    public CategoryDTO map(Category category) {
        return new CategoryDTO(category);
    }

    @Override
    public Category map(CategoryDTO categoryDTO) {
        return new Category(categoryDTO);
    }

    @Override
    public SoldItems purchase(String name, LocalDate date) {
        Category category = map(getCategory(name));
        validateItems(category);
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

    @Override
    public String delete(String name) {
        CategoryDTO categoryDTO = getCategory(name);
        categoryRepository.delete(map(categoryDTO));
        return categoryDTO.getName() + " " + categoryDTO.getPrice();
    }

    private void validateItems(Category category) {
        if (category.getItems() <= 0) {
            throw new CategoryItemsException("Items is null or less");
        }
    }


}
