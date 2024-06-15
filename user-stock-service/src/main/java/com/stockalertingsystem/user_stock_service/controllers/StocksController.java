package com.stockalertingsystem.user_stock_service.controllers;

import com.stockalertingsystem.user_stock_service.dto.StockRequest;
import com.stockalertingsystem.user_stock_service.model.Stock;
import com.stockalertingsystem.user_stock_service.service.StockService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/stocks")
@Slf4j
public class StocksController {

  // private static final Logger logger = (Logger) LoggerFactory.getLogger(StocksController.class);

  private final StockService stockService;

  public StocksController(StockService stockService) {
    this.stockService = stockService;
  }

  @PostMapping
  public ResponseEntity<Stock> addStock(@RequestBody StockRequest stockRequest) {
    log.info(
        "Creating stocks, stock_name {} stock_symbol {}",
        stockRequest.getStockName(),
        stockRequest.getStockSymbol());
    return ResponseEntity.accepted().body(stockService.addStock(stockRequest));
  }

  @PostMapping("/{id}")
  public String addDummy(@PathVariable String id) {
    return "Added";
  }

  @GetMapping
  public ResponseEntity<List<Stock>> getStocks() {
    log.info("Getting all stocks data");
    return ResponseEntity.accepted().body(stockService.getStocks());
  }
}
