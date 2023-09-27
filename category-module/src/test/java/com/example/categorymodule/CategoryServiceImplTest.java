package com.example.categorymodule;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.example.categorymodule.dao.CategoryDao;
import com.example.categorymodule.dto.CategoryDto;
import com.example.categorymodule.entity.Category;
import com.example.categorymodule.exception.RecordNotFoundException;
import com.example.categorymodule.service.CategoryServiceImpl;

public class CategoryServiceImplTest {

    private CategoryDao categoryDao;
    private ModelMapper modelMapper;
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup() {
        categoryDao = mock(CategoryDao.class);
        modelMapper = mock(ModelMapper.class);
        categoryService = new CategoryServiceImpl(categoryDao, modelMapper);
    }

    @Test
    public void testCreateCategory_Success() throws RecordNotFoundException {
        CategoryDto categoryDto = new CategoryDto(1, "Music");
        Category category = new Category(1, "Music");

        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
        when(categoryDao.findByCategoryName(category.getCategoryName())).thenReturn(null);
        when(categoryDao.save(category)).thenReturn(category);
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);

        CategoryDto result = categoryService.createCategory(categoryDto);

        assertNotNull(result);
        assertEquals(categoryDto, result);

        verify(modelMapper, times(1)).map(categoryDto, Category.class);
        verify(categoryDao, times(1)).findByCategoryName(category.getCategoryName());
        verify(categoryDao, times(1)).save(category);
        verify(modelMapper, times(1)).map(category, CategoryDto.class);
    }

    @Test
    public void testCreateCategory_CategoryAlreadyPresent() {
        CategoryDto categoryDto = new CategoryDto(1, "Music");
        Category category = new Category(1, "Music");

        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
        when(categoryDao.findByCategoryName(category.getCategoryName())).thenReturn(category);

        assertThrows(RecordNotFoundException.class, () -> categoryService.createCategory(categoryDto));

        verify(modelMapper, times(1)).map(categoryDto, Category.class);
        verify(categoryDao, times(1)).findByCategoryName(category.getCategoryName());
        verify(categoryDao, times(0)).save(category);
        verify(modelMapper, times(0)).map(category, CategoryDto.class);
    }

    @Test
    public void testUpdateCategory_Success() throws RecordNotFoundException {
        CategoryDto categoryDto = new CategoryDto(1, "Music");
        Category category = new Category(1, "Music");

        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
        when(categoryDao.save(category)).thenReturn(category);
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);

        CategoryDto result = categoryService.updateCategory(categoryDto);

        assertNotNull(result);
        assertEquals(categoryDto, result);

        verify(modelMapper, times(1)).map(categoryDto, Category.class);
        verify(categoryDao, times(1)).save(category);
        verify(modelMapper, times(1)).map(category, CategoryDto.class);
    }

    @Test
    public void testDeleteCategory_Success() throws RecordNotFoundException {
        int categoryId = 1;
        CategoryDto categoryDto = new CategoryDto(1, "Music");
        Category category = new Category(1, "Music");
        Optional<Category> optionalCategory = Optional.of(category);

        when(categoryDao.findById(categoryId)).thenReturn(optionalCategory);
        doNothing().when(categoryDao).deleteById(category.getId());
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);

        CategoryDto result = categoryService.deleteCategory(categoryId);

        assertNotNull(result);
        assertEquals(categoryDto, result);

        verify(categoryDao, times(1)).findById(categoryId);
        verify(categoryDao, times(1)).deleteById(category.getId());
        verify(modelMapper, times(1)).map(category, CategoryDto.class);
    }

    @Test
    public void testDeleteCategory_CategoryNotFound() {
        int categoryId = 1;
        Optional<Category> optionalCategory = Optional.empty();

        when(categoryDao.findById(categoryId)).thenReturn(optionalCategory);

        assertThrows(RecordNotFoundException.class, () -> categoryService.deleteCategory(categoryId));

        verify(categoryDao, times(1)).findById(categoryId);
        verify(categoryDao, times(0)).deleteById(anyInt());
        verify(modelMapper, times(0)).map(any(), any());
    }

    @Test
    public void testViewAllCategories_Success() throws RecordNotFoundException {
        Category category1 = new Category(1, "Music");
        Category category2 = new Category(2, "Movie");
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        CategoryDto categoryDto1 = new CategoryDto(1, "Music");
        CategoryDto categoryDto2 = new CategoryDto(2, "Movie");
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categoryDtos.add(categoryDto1);
        categoryDtos.add(categoryDto2);

        when(categoryDao.findAll()).thenReturn(categories);
        when(modelMapper.map(category1, CategoryDto.class)).thenReturn(categoryDto1);
        when(modelMapper.map(category2, CategoryDto.class)).thenReturn(categoryDto2);

        List<CategoryDto> result = categoryService.viewAllCategories();

        assertNotNull(result);
        assertEquals(categoryDtos, result);

        verify(categoryDao, times(1)).findAll();
        verify(modelMapper, times(1)).map(category1, CategoryDto.class);
        verify(modelMapper, times(1)).map(category2, CategoryDto.class);
    }

    @Test
    public void testViewAllCategories_NoCategoriesFound() {
        List<Category> categories = new ArrayList<>();

        when(categoryDao.findAll()).thenReturn(categories);

        assertThrows(RecordNotFoundException.class, () -> categoryService.viewAllCategories());

        verify(categoryDao, times(1)).findAll();
        verify(modelMapper, times(0)).map(any(), any());
    }
}
