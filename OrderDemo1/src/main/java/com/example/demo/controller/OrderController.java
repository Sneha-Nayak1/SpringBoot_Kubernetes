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

import com.example.demo.entity.OrderTable;
import com.example.demo.entity.Product;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/post")
	public ResponseEntity<String> saveOrders(@RequestBody OrderTable order){
		orderService.saveOrders(order);
		orderService.mesgToProduct("Order placed for "+order.getName()+" product");
		return new ResponseEntity<String>("Orders got saved sucessfully!",HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<OrderTable>> getAllOrder(){
		return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<OrderTable> getById(@PathVariable("id") Long orderId){
		return new ResponseEntity<>(orderService.getById(orderId),HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<OrderTable> updateOrder(@RequestBody OrderTable order, @PathVariable Long id){
		return new ResponseEntity<>(orderService.updateOrders(order, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long id){
		orderService.deleteOrder(id);
		return new ResponseEntity<>("Order Deleted Successfully!",HttpStatus.OK);
	}
	
	@GetMapping("rest/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		return new ResponseEntity<>(orderService.getProductById(id),HttpStatus.OK);
	}

}
