package com.intelliarts.test.model;

import com.intelliarts.test.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private String name;
    private int items;

    public Category(CategoryDTO categoryDTO) {
        this.id = categoryDTO.getId();
        this.price = categoryDTO.getPrice();
        this.name = categoryDTO.getName();
        this.items = categoryDTO.getItems();
    }
}
