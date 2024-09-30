package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductServiceInterface;

@Service
public class ProductServiceImpl implements ProductServiceInterface{
	
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getById(Long id) {
		Optional<Product> product=productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}else {
			throw new ResourceNotFoundException("Product","ID",id);
		}
		
	}

	

	@Override
	public void deleteProduct(Long id) {
		Optional<Product> product=productRepository.findById(id);
		if(product.isPresent()) {
			productRepository.deleteById(id);
		}else {
			throw new ResourceNotFoundException("Product","ID",id);
		}
		
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product, Long id) {
		Product product1=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","ID",id));
		product1.setName(product.getName());
		return productRepository.save(product1);
	}

	@Override
	public List<Product> searchProducts(Double minPrice, Double maxPrice) {
		return productRepository.findByPriceBetween(minPrice, maxPrice);
	}

}
