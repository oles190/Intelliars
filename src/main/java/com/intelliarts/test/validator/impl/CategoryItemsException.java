package com.intelliarts.test.validator.impl;

import com.intelliarts.test.model.Category;
import com.intelliarts.test.validator.CategoryCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class CategoryItemsException implements CategoryCreateValidator {
    @Override
    public void validate(Category category) {
        if (category.getItems() < 0) {
            throw new com.intelliarts.test.exception.category.
                    CategoryItemsException("item can't be less than 0!");
        }
    }
}
