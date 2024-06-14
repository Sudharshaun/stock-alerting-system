package com.stockalertingsystem.user_stock_service.service.impl;

import com.stockalertingsystem.user_stock_service.dto.StockRequest;
import com.stockalertingsystem.user_stock_service.model.Stock;
import com.stockalertingsystem.user_stock_service.repository.StockRepository;
import com.stockalertingsystem.user_stock_service.service.StockService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockServiceImpl implements StockService {
  @Autowired private StockRepository stockRepository;

  @Override
  public Stock addStock(StockRequest stockRequest) {
    Optional<Stock> existingStock = stockRepository.findBySymbol(stockRequest.getStockSymbol());
    if (existingStock.isEmpty()) {
      Stock stock = new Stock();
      stock.setName(stockRequest.getStockName());
      stock.setSymbol(stockRequest.getStockSymbol());
      stockRepository.save(stock);
      return stock;
    }
    return new Stock();
  }

  @Override
  public List<Stock> getStocks() {
    return stockRepository.findAll();
  }
}
