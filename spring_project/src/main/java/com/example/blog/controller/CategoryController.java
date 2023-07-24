package com.example.blog.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.blog.payLoads.ApiResponse;
import com.example.blog.payLoads.CategoryDto;
import com.example.blog.payLoads.UserDto;
import com.example.blog.service.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/category/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	// create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}
	
	// update category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId){
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	// delete category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(new ApiResponse("deleted category successfully",true),HttpStatus.OK);
	}
	
	// get category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId){
		CategoryDto getCategory = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(getCategory, HttpStatus.OK);
	}
	
	// get all categories
	@GetMapping("/")
	public ResponseEntity<java.util.List<CategoryDto>> getAllCategory(){
//		List getAllCategory = (List) this.categoryService.getAllCategory();
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}
}