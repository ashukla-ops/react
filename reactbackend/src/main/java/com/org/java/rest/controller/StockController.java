package com.org.java.rest.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.java.rest.model.StockDTO;
import com.org.java.rest.service.StockServiceImpl;


@RestController
@RequestMapping("controller")
@CrossOrigin()
public class StockController {

	@Autowired
	private StockServiceImpl stockService;
	
	@GetMapping(value = "/getStocks")
	public List<StockDTO> getStockList(){
		return stockService.stockList();		
	}
	
	@PostMapping(value = "/createStock")
	public ResponseEntity<Object> saveFuel(@Validated @RequestBody StockDTO stockDto){	
		stockService.createStock(stockDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/getStock/{stockId}")
	public Optional<StockDTO> getStockById(@PathVariable Long stockId)
	{
		return stockService.findStockById(stockId);		
	}
}
