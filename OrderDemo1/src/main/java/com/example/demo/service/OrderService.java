package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.OrderTable;
import com.example.demo.entity.Product;

public interface OrderService {
	void saveOrders(OrderTable order);
	List<OrderTable> getAllOrders();
	OrderTable getById(Long id);
	OrderTable updateOrders(OrderTable order, Long id);
	void deleteOrder(Long id);
	Product getProductById(Long id);
	boolean mesgToProduct(String message);
	
}
