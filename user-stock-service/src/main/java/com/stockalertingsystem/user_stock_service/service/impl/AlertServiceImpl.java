package com.stockalertingsystem.user_stock_service.service.impl;

import com.stockalertingsystem.user_stock_service.dto.AlertsResponse;
import com.stockalertingsystem.user_stock_service.dto.SubscriptionRequest;
import com.stockalertingsystem.user_stock_service.model.Alerts;
import com.stockalertingsystem.user_stock_service.model.Stock;
import com.stockalertingsystem.user_stock_service.model.User;
import com.stockalertingsystem.user_stock_service.repository.AlertsRepository;
import com.stockalertingsystem.user_stock_service.repository.StockRepository;
import com.stockalertingsystem.user_stock_service.repository.UserRepository;
import com.stockalertingsystem.user_stock_service.service.AlertService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertServiceImpl implements AlertService {
  @Autowired private UserRepository userRepository;

  @Autowired private StockRepository stockRepository;

  @Autowired private AlertsRepository alertsRepository;

  @Override
  public AlertsResponse add(SubscriptionRequest subscriptionRequest) {
    User user =
        userRepository
            .findById(subscriptionRequest.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
    Stock stock =
        stockRepository
            .findById(subscriptionRequest.getStockId())
            .orElseThrow(() -> new RuntimeException("Stock not found"));
    Alerts alerts = new Alerts();
    alerts.setUser(user);
    alerts.setStock(stock);
    alerts.setThreshold(subscriptionRequest.getThreshold());
    alerts.setConditionType(subscriptionRequest.getConditionType());
    alertsRepository.save(alerts);
    return null;
  }

  @Override
  public List<AlertsResponse> getAlertsForUser(long id) {
    return List.of();
  }
}
