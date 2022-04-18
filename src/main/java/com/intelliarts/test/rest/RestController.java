package com.intelliarts.test.rest;


import com.intelliarts.test.dto.CategoryDTO;
import com.intelliarts.test.dto.SoldItemsDTO;
import com.intelliarts.test.model.SoldItems;
import com.intelliarts.test.service.CategoryService;
import com.intelliarts.test.service.SoldItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/categories")
public class RestController {

    private final CategoryService categoryService;
    private final SoldItemsService soldItemsService;

    @Autowired
    public RestController(CategoryService categoryService, SoldItemsService soldItemsService) {
        this.categoryService = categoryService;
        this.soldItemsService = soldItemsService;
    }


    @PostMapping()
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);

    }


    @GetMapping("/byName/{name}")
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategory(name);
    }

    @PostMapping("/items")
    public CategoryDTO addItems(@RequestParam String name, @RequestParam int count) {
        return categoryService.addItems(name, count);
    }

    @PostMapping("/purchase/{name}/{date}")
    public SoldItems purchase(@PathVariable String name, @PathVariable
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return categoryService.purchase(name, date);
    }

    @GetMapping("/list")
    public List<CategoryDTO> list() {
        return categoryService.getAll();
    }

    @DeleteMapping("/{name}")
    public String clear(@PathVariable String name) {
        return categoryService.delete(name);
    }

    @GetMapping("/report/{date}")
    public List<SoldItemsDTO> reportSinceDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return soldItemsService.reportSinceDate(date);
    }

    @GetMapping("/totalForMonth/{date}")
    public String totalForMonth(@PathVariable YearMonth date) {

        return soldItemsService.totalForMonth(date);
    }


}
