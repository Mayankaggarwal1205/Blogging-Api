package com.example.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Category;
import com.example.blog.payLoads.CategoryDto;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	
}
