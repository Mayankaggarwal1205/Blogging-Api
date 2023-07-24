package com.example.blog.exceptions;

import lombok.Getter;
import lombok.Setter;


public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue ));
        // string.format returns the formatted string by a given locale, format, and argument
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
	

	public String getResourceName() {
		return resourceName;
	}



	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}



	public String getFieldName() {
		return fieldName;
	}



	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}



	public long getFieldValue() {
		return fieldValue;
	}



	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}
}
