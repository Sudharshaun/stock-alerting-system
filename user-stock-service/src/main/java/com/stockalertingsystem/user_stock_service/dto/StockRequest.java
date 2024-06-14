package com.stockalertingsystem.user_stock_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockRequest {
  private String stockName;
  private String stockSymbol;

  public StockRequest(String stockName, String stockSymbol) {
    this.stockName = stockName;
    this.stockSymbol = stockSymbol;
  }
}
