package com.org.java.rest;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.org.java.rest.service.StockServiceImpl;

@Profile("test")
@Configuration
public class StockServiceTestConfiguration {
	
	   @Bean
	   @Primary
	   public StockServiceImpl productService() {
	      return Mockito.mock(StockServiceImpl.class);
	   }

}
