package com.intelliarts.test.validator.impl;

import com.intelliarts.test.exception.category.CategoryNameException;
import com.intelliarts.test.model.Category;
import com.intelliarts.test.validator.CategoryCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class CategoryNameValidator implements CategoryCreateValidator {
    @Override
    public boolean validate(Category category) {

        if (category.getName() == null || category.getName().isEmpty()) {
            throw new CategoryNameException("Name can't be null or empty!");
        }
        return true;
    }
}
