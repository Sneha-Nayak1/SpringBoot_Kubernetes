package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductServiceInterface;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductServiceInterface productService;
	
	@PostMapping("/post")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") Long proId){
		return new ResponseEntity<>(productService.getById(proId),HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
		return new ResponseEntity<>(productService.updateProduct(product, id),HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product deleted Successfully!", HttpStatus.OK);
	}
	
	@GetMapping("/search/{minPrice}/{maxPrice}")
	public ResponseEntity<List<Product>> searchByPrice(@PathVariable Double minPrice, @PathVariable Double maxPrice){
		return new ResponseEntity<>(productService.searchProducts(minPrice, maxPrice),HttpStatus.OK);
	}
	
	//@PutMapping("updateByPrice/{id}")
	
}
