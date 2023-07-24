package com.example.blog.payLoads;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryDto {
	
	
	private int category_id;
	
	@NotEmpty
//	@Size(min=1, max=25,message="You have entered incorrect title...")
	@NotNull
	@NotEmpty
	@Size(min =4,message="Title must be of min of 4 characters")
	private String category_title;
	
	@NotNull
//	@Size(min=10, max=25,message="You have entered incorrect description...")
	private String category_desc;

	@Override
	public String toString() {
		return "CategoryDto [category_id=" + category_id + ", category_title=" + category_title + ", category_desc="
				+ category_desc + "]";
	}

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDto(int category_id, String category_title, String category_desc) {
		super();
		this.category_id = category_id;
		this.category_title = category_title;
		this.category_desc = category_desc;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_title() {
		return category_title;
	}

	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}

	public String getCategory_desc() {
		return category_desc;
	}

	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}

}
