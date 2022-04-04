package com.intelliarts.test.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldItemsDTO implements Comparable<SoldItemsDTO> {

    private String name;
    private double price;
    private Long items;


    @Override
    public int compareTo(SoldItemsDTO o) {
        return this.name.compareTo(o.getName());
    }
}
