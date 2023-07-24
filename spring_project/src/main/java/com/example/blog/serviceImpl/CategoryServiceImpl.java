package com.example.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Category;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payLoads.CategoryDto;
import com.example.blog.repo.CategoryRepo;
import com.example.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category created = this.categoryRepo.save(cat);
		return this.modelMapper.map(created, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow( () -> new ResourceNotFoundException("Category", " id ", categoryId) );
		cat.setCategory_desc(categoryDto.getCategory_desc());
		cat.setCategory_title(categoryDto.getCategory_title());
		Category updated = this.categoryRepo.save(cat);
		return this.modelMapper.map(updated, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		this.categoryRepo.deleteById(categoryId);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow( () -> new ResourceNotFoundException("Category", " id ", categoryId) );
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> all = this.categoryRepo.findAll();
		List<CategoryDto> categories = all.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categories;
	}

}
