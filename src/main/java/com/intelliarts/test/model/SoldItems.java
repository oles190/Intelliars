package com.intelliarts.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor

public class SoldItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private LocalDate date;
    private int items;

    public SoldItems(String name, double price, LocalDate date, int items) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.items = items;
    }

}
