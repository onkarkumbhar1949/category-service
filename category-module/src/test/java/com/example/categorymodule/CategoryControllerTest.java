package com.example.categorymodule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.example.categorymodule.controller.CategoryController;
import com.example.categorymodule.dto.CategoryDto;
import com.example.categorymodule.exception.RecordNotFoundException;
import com.example.categorymodule.service.CategoryService;



class CategoryControllerTest {

    private CategoryService categoryService;
    private CategoryController categoryController;
    

    @BeforeEach
    public void setup() {
        categoryService = mock(CategoryService.class);
        
        categoryController = new CategoryController(categoryService);
    }

    @Test
    void testAddCategory_Success() throws RecordNotFoundException {
        CategoryDto categoryDto = new CategoryDto(1, "Music");

        when(categoryService.createCategory(categoryDto)).thenReturn(categoryDto);

        CategoryDto result = categoryController.addCategory(categoryDto);

        assertNotNull(result);
        assertEquals(categoryDto, result);

        verify(categoryService, times(1)).createCategory(categoryDto);
    }

    @Test
    void testUpdateCategory_Success() throws RecordNotFoundException {
        CategoryDto categoryDto = new CategoryDto(1, "Music");

        when(categoryService.updateCategory(categoryDto)).thenReturn(categoryDto);

        CategoryDto result = categoryController.updateCategory(categoryDto);

        assertNotNull(result);
        assertEquals(categoryDto, result);

        verify(categoryService, times(1)).updateCategory(categoryDto);
    }

    @Test
    void testDeleteCategory_Success() throws RecordNotFoundException {
        int categoryId = 1;

        ResponseEntity<HttpStatus> response = categoryController.deleteCategory(categoryId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(categoryService, times(1)).deleteCategory(categoryId);
    }

    @Test
    void testViewAllCategories_Success() throws RecordNotFoundException {
        CategoryDto categoryDto1 = new CategoryDto(1, "Music");
        CategoryDto categoryDto2 = new CategoryDto(2, "Movie");
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categoryDtos.add(categoryDto1);
        categoryDtos.add(categoryDto2);

        when(categoryService.viewAllCategories()).thenReturn(categoryDtos);

        List<CategoryDto> result = categoryController.viewAllCategories();

        assertNotNull(result);
        assertEquals(categoryDtos, result);

        verify(categoryService, times(1)).viewAllCategories();
    }

 /*  @Test
    void testHandleRecordNotFoundException_Success() {
       RecordNotFoundException ex = new RecordNotFoundException("Category not found!");
       WebRequest request = Mockito.mock(WebRequest.class);

       ResponseEntity<String> response = categoryController.handleRecordNotFoundException(ex, request);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ex.getMessage(), response.getBody());

        
    }*/
}

