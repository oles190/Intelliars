package com.intelliarts.test.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
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
