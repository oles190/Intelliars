package com.intelliarts.test.service.impl;

import com.intelliarts.test.TestApplication;
import com.intelliarts.test.dto.CategoryDTO;
import com.intelliarts.test.model.Category;
import com.intelliarts.test.model.SoldItems;
import com.intelliarts.test.repository.CategoryRepository;
import com.intelliarts.test.service.CategoryService;
import com.intelliarts.test.service.SoldItemsService;
import com.intelliarts.test.validator.CategoryCreateValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestApplication.class)
class CategoryServiceImplTest {

    @Mock
    SoldItemsService soldItemsService;
    private CategoryService categoryService;
    @Autowired
    private List<CategoryCreateValidator> categoryCreateValidator;
    @Mock
    private CategoryRepository categoryRepository;
    private static final List<Category> categoryDTOS = initCategories();


    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository, soldItemsService,
                categoryCreateValidator);
    }


    @Test
    void getCategorySuccessfully() {
        Category category = categoryDTOS.get(2);
        Mockito.when(categoryRepository.getCategoryByName(category.getName())).thenReturn(category);
        CategoryDTO notNull = categoryService.getCategory(category.getName());
        assertNotNull(notNull);
        Mockito.verify(categoryRepository, Mockito.times(1))
                .getCategoryByName(category.getName());
    }

    @Test
    void getCategoryThrow() {
        Mockito.when(categoryRepository.getCategoryByName("Test")).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> categoryService.getCategory("Test"));
        Assertions.assertEquals("Category is null", exception.getMessage());
    }


    @Test
    void addCategoryThrow() {
        Category category = categoryDTOS.get(2);
        CategoryDTO categoryDTO = categoryService.map(category);
        Mockito.when(categoryRepository.getCategoryByName(category.getName())).thenReturn(category);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.addCategory(categoryDTO));
        Assertions.assertEquals("Already Exist", exception.getMessage());
        Mockito.verify(categoryRepository, Mockito.never()).save(category);
    }

    @Test
    void addCategorySuccessfully() {
        Category category = categoryDTOS.get(2);
        CategoryDTO categoryDTO = new CategoryDTO(category);
        Mockito.when(categoryRepository.getCategoryByName(category.getName())).thenReturn(null);
        CategoryDTO create = categoryService.addCategory(categoryDTO);
        Mockito.verify(categoryRepository, Mockito.times(1))
                .save(category);
        Mockito.verify(categoryRepository, Mockito.times(1))
                .getCategoryByName(category.getName());
        assertNotNull(create);
    }

    @Test
    void getAllTest() {
        List<Category> expected = initCategories();
        Mockito.when(categoryRepository.findAll()).thenReturn(expected);
        List<CategoryDTO> allCategory = categoryService.getAll();
        Assertions.assertNotNull(allCategory);
        Assertions.assertEquals(3, allCategory.size());
        List<Category> actual = allCategory.stream().map(one -> categoryService.map(one)).collect(Collectors.toList());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addItemsSuccessfully() {
        Category category = initCategories().get(2);
        Mockito.when(categoryRepository.getCategoryByName(category.getName())).thenReturn(category);
        CategoryDTO created = categoryService.addItems(category.getName(), 1);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(categoryService.map(created));
        assertEquals(category.getItems() + 1, created.getItems());
    }

    @Test
    void addItemsThrow() {
        Category category = initCategories().get(2);
        Mockito.when(categoryRepository.getCategoryByName(category.getName())).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> categoryService.addItems(category.getName(), 1));
        assertEquals("Category is null", exception.getMessage());
    }

    @Test
    void purchaseSuccessfully() {
        Category category = initCategories().get(2);
        Mockito.when(categoryRepository.getCategoryByName(category.getName())).thenReturn(category);
        SoldItems created = categoryService.purchase(category.getName(), LocalDate.of(2022, 2, 12));
        Mockito.verify(soldItemsService, Mockito.times(1)).create(created);
    }

    @Test
    void purchaseThrow() {
        Category category = initCategories().get(2);
        Mockito.when(categoryRepository.getCategoryByName(category.getName())).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> categoryService.purchase(category.getName(), LocalDate.of(2022, 12, 8)));
        assertEquals("Category is null", exception.getMessage());
    }

    @Test
    void deleteSuccessfully() {
        Category category = initCategories().get(2);
        Mockito.when(categoryRepository.getCategoryByName(category.getName())).thenReturn(category);
        categoryService.delete(category.getName());
        Mockito.verify(categoryRepository, Mockito.times(1)).delete(category);
    }

    @Test
    void deleteThrow() {
        Category category = initCategories().get(2);
        Mockito.when(categoryRepository.getCategoryByName(category.getName())).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.delete(category.getName()));
        Mockito.verify(categoryRepository, Mockito.never()).delete(category);
        assertEquals("Category is null", exception.getMessage());
    }


    private static List<Category> initCategories() {
        return new ArrayList<>(Arrays.asList(
                new Category(1L, 2.2, "Test", 2),
                new Category(2L, 2.2, "Test1", 2),
                new Category(3L, 2.2, "Test2", 2)));
    }
}
