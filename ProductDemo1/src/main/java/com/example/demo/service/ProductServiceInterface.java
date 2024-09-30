package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;



public interface ProductServiceInterface {
	
	void saveProduct(Product product);
	List<Product> getAllProducts();
	Product getById(Long id);
	Product updateProduct(Product product,Long id);
	void deleteProduct(Long id);
	List<Product> searchProducts(Double minPrice,Double maxPrice);

}
