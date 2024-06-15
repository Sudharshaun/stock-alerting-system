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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertServiceImpl implements AlertService {
  @Autowired private UserRepository userRepository;

  @Autowired private StockRepository stockRepository;

  @Autowired private AlertsRepository alertsRepository;

  @Override
  public AlertsResponse add(SubscriptionRequest subscriptionRequest) {
    User user = getUser(subscriptionRequest.getUserId());
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
  public List<Alerts> getAlertsForUser(long id) {
    User user = getUser(id);
    Optional<Alerts> alerts = alertsRepository.findByUserId(user.getId());
    return alerts.isPresent() ? alerts.stream().toList() : List.of();
  }

  private User getUser(long id) {
    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    return user;
  }
}
