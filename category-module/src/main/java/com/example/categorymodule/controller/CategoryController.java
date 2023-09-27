package com.example.categorymodule.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.categorymodule.dto.CategoryDto;
import com.example.categorymodule.exception.RecordNotFoundException;
import com.example.categorymodule.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping("/addCategory")
	public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) throws RecordNotFoundException {
		return this.categoryService.createCategory(categoryDto);
	}

	@PutMapping("/updateCategory")
	public CategoryDto updateCategory(@RequestBody CategoryDto category) throws RecordNotFoundException {
		return this.categoryService.updateCategory(category);
	}

	@DeleteMapping("/deleteCategoryById/{id}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable int id) throws RecordNotFoundException {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/viewAllCategories")
	public List<CategoryDto> viewAllCategories() throws RecordNotFoundException {
		return this.categoryService.viewAllCategories();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
	public final ResponseEntity<String> handleRecordNotFoundException(Exception ex, WebRequest request) {

		return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
