package com.example.categorymodule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;


import org.junit.jupiter.api.Test;

import com.example.categorymodule.dto.CategoryDto;
import com.example.categorymodule.entity.Category;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


class CategoryTest {

    @Test
    void testCategoryConstructor() {
        int id = 1;
        String categoryName = "Music";
        Category category = new Category(id, categoryName);

        assertEquals(id, category.getId());
        assertEquals(categoryName, category.getCategoryName());
    }

    @Test
    void testGettersAndSetters() {
        int id = 1;
        String categoryName = "Music";
        Category category = new Category();

        category.setId(id);
        category.setCategoryName(categoryName);

        assertEquals(id, category.getId());
        assertEquals(categoryName, category.getCategoryName());
    }

    @Test
    void testToString() {
        int id = 1;
        String categoryName = "Music";
        Category category = new Category(id, categoryName);

        String expectedToString = "Category [id=" + id + ", categoryName=" + categoryName + "]";
        assertEquals(expectedToString, category.toString());
    }

    @Test
    void testCategoryNameNotNull() {
        Category category = new Category();
        category.setId(1);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Category name cannot be null!")));
    }

    @Test
    void testCategoryNameNotBlank() {
        Category category = new Category();
        category.setId(1);
        category.setCategoryName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Category name connot be blank!")));
    }
    
    @Test
    void testCategoryDtoConstructor() {
        int id = 1;
        String categoryName = "Music";
        CategoryDto categoryDto = new CategoryDto(id, categoryName);

        assertEquals(id, categoryDto.getId());
        assertEquals(categoryName, categoryDto.getCategoryName());
    }

    @Test
    void testGettersAndSetters1() {
        int id = 1;
        String categoryName = "Music";
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(id);
        categoryDto.setCategoryName(categoryName);

        assertEquals(id, categoryDto.getId());
        assertEquals(categoryName, categoryDto.getCategoryName());
    }
}

