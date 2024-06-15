package com.stockalertingsystem.user_stock_service.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequest {
  private long userId;
  private long stockId;
  private String conditionType;
  private BigDecimal threshold;
}
