package com.stockalertingsystem.user_stock_service.service;

import com.stockalertingsystem.user_stock_service.dto.StockRequest;
import com.stockalertingsystem.user_stock_service.model.Stock;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface StockService {

  public Stock addStock(StockRequest stockRequest);

  public List<Stock> getStocks();
}
