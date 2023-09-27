package com.example.categorymodule.dto;

public class CategoryDto {
	

	private int id;
	private String categoryName;
	
	public CategoryDto() {
		super();
	}

	public CategoryDto(int id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
	
	

}
