package com.org.java.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.java.rest.model.StockDTO;
import com.org.java.rest.repo.StockRepository;

@Service
public class StockServiceImpl {

	@Autowired
	private StockRepository repo;
	
	public List<StockDTO> stockList()
	{
		return repo.findAll();		
	}
	
	public void createStock(StockDTO stockDto)
	{
		stockDto.setMarketPrice(0.0f);
		stockDto.setMarketPrice((stockDto.getAskPrice()+stockDto.getBidPrice())/2);
		repo.save(stockDto);
	}
	
	public Optional<StockDTO> findStockById(Long id)
	{
		return repo.findById(id);	
	}
	
	public void deleteStock(Long id)
	{
		repo.deleteById(id);
	}
}
