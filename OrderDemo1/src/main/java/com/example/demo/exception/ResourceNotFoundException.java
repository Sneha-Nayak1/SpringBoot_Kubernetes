package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String name;
	private String field;
	private Object id;
	
	public ResourceNotFoundException(String name, String field, Object id) {
		super(String.format("%s not found with %s: %s", name,field,id));
		this.name = name;
		this.field = field;
		this.id = id;
	}

}
