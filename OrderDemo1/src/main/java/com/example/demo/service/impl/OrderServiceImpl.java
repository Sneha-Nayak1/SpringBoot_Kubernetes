package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.AppConstants;
import com.example.demo.entity.OrderTable;
import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public void saveOrders(OrderTable order) {
		orderRepository.save(order);
	}

	@Override
	public List<OrderTable> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public OrderTable getById(Long id) {
		Optional<OrderTable> order=orderRepository.findById(id);
		if(order.isPresent()) {
			return order.get();
		}else {
			throw new ResourceNotFoundException("Order","ID",id);
		}
	}

	@Override
	public OrderTable updateOrders(OrderTable order, Long id) {
		OrderTable order1=orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order","ID",id));
		order1.setDeliveryDate(order.getDeliveryDate());
		return orderRepository.save(order1);
	}

	@Override
	public void deleteOrder(Long id) {
		Optional<OrderTable> order=orderRepository.findById(id);
		if(order.isPresent()) {
			orderRepository.deleteById(id);
		}else {
			throw new ResourceNotFoundException("Order","ID",id);
		}
	}

	@Override
	public Product getProductById(Long id) {
		String URL="http://localhost:8082/product/getById/"+id;
		ResponseEntity<Product> response =restTemplate.getForEntity(URL, Product.class);
		return response.getBody();
	}

	@Override
	public boolean mesgToProduct(String message) {
		this.kafkaTemplate.send(AppConstants.MESSAGE,message);
		return true;
	}

	

}
