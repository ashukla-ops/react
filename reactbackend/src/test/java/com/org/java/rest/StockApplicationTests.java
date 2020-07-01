package com.org.java.rest;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.org.java.rest.model.StockDTO;
import com.org.java.rest.service.StockServiceImpl;
import junit.framework.Assert;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class StockApplicationTests {
	
	   @Autowired
	   private StockServiceImpl stockService;
	   
	   @SuppressWarnings("deprecation")
	   @Test
	   public void whenStockServiceIsCalling() {
	      Mockito.when(stockService.stockList()).thenAnswer(null);
	      List<StockDTO> stockList = stockService.stockList();
	      Assert.assertEquals("find Stock list", stockList);
	   }
	   
	   @SuppressWarnings("deprecation")
	   @Test
	   public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() {
	      Mockito.when(stockService.findStockById(1l)).thenAnswer(null);
	      Optional<StockDTO> stock = stockService.findStockById(1l);
	      Assert.assertEquals("find stock by id", stock);
	   }


}
