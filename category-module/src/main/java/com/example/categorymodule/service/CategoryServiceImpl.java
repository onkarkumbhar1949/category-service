package com.example.categorymodule.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.categorymodule.dao.CategoryDao;
import com.example.categorymodule.dto.CategoryDto;
import com.example.categorymodule.entity.Category;
import com.example.categorymodule.exception.RecordNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;

	private ModelMapper mapper;

	public CategoryServiceImpl(CategoryDao categoryDao, ModelMapper mapper) {
		super();
		this.categoryDao = categoryDao;
		this.mapper = mapper;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) throws RecordNotFoundException {
		Category category = mapToEntity(categoryDto);
		Category existingCategory = categoryDao.findByCategoryName(category.getCategoryName());
		if (existingCategory != null) {
			throw new RecordNotFoundException("Category already present!!");
		}
		return mapToDTO(categoryDao.save(category));
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) throws RecordNotFoundException {
		return mapToDTO(categoryDao.save(mapToEntity(categoryDto)));
	}

	@Override
	public CategoryDto deleteCategory(int id) throws RecordNotFoundException {
		Optional<Category> category = categoryDao.findById(id);
		if (category.isPresent()) {
			categoryDao.deleteById(category.get().getId());
		} else {
			throw new RecordNotFoundException("Category not found!!");
		}
		return mapToDTO(category.get());
	}

	@Override
	public List<CategoryDto> viewAllCategories() throws RecordNotFoundException {
		List<Category> categories = categoryDao.findAll();
		if (categories.isEmpty()) {
			throw new RecordNotFoundException("No categories found!!");
		}
		return categories.stream().map(this::mapToDTO).toList();
	}

	private CategoryDto mapToDTO(Category category) {
		return mapper.map(category, CategoryDto.class);
	}

	private Category mapToEntity(CategoryDto categoryDto) {
		return mapper.map(categoryDto, Category.class);
	}
}
