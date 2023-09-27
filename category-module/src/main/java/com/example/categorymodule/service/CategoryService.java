package com.example.categorymodule.service;



import java.util.List;

import com.example.categorymodule.dto.CategoryDto;
import com.example.categorymodule.exception.RecordNotFoundException;



public interface CategoryService {

	public CategoryDto deleteCategory(int id) throws RecordNotFoundException;
	public List<CategoryDto> viewAllCategories() throws RecordNotFoundException;
	CategoryDto createCategory(CategoryDto categoryDto) throws RecordNotFoundException;
	CategoryDto updateCategory(CategoryDto categoryDto) throws RecordNotFoundException;
	
	
}
