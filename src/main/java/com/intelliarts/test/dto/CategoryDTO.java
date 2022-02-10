package com.intelliarts.test.dto;

import com.intelliarts.test.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO implements Comparable<CategoryDTO> {
    private Long id;
    private double price;
    private String name;
    private int items;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.price = category.getPrice();
        this.name = category.getName();
        this.items = category.getItems();
    }

    public CategoryDTO(double price, String name, int items) {
        this.price = price;
        this.name = name;
        this.items = items;
    }

    @Override
    public int compareTo(CategoryDTO o) {
        return o.getItems() - this.getItems();
    }
}


