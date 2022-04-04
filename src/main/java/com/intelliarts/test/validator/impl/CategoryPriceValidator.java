package com.intelliarts.test.validator.impl;

import com.intelliarts.test.exception.category.CategoryPriceException;
import com.intelliarts.test.model.Category;
import com.intelliarts.test.validator.CategoryCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class CategoryPriceValidator implements CategoryCreateValidator {
    @Override
    public boolean validate(Category category) {

        if (category.getPrice() <= 0) {
            throw new CategoryPriceException("Price can't be 0 or less than 0!");
        }
        return true;

    }
}
