package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfiguration {
	
	@KafkaListener(topics=AppConstants.MESSGAE, groupId=AppConstants.GROUP_ID)
	public void mesgToProduct(String value) {
		System.out.println(value);
	}
}
