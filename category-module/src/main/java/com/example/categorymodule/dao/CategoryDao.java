package com.example.categorymodule.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.categorymodule.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer>{

	//com.example.categorymodule.entity.Category saveAll(com.example.categorymodule.entity.Category category);
	
	public Category save(Category category);
	
	public Category findByCategoryName(String CategoryName);
	
	public Optional<Category> findById(int id);
	
	
	
}
