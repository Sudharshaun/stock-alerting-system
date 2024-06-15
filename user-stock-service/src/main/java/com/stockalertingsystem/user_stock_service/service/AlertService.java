package com.stockalertingsystem.user_stock_service.service;

import com.stockalertingsystem.user_stock_service.dto.AlertsResponse;
import com.stockalertingsystem.user_stock_service.dto.SubscriptionRequest;
import com.stockalertingsystem.user_stock_service.model.Alerts;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AlertService {

  public AlertsResponse add(SubscriptionRequest subscriptionRequest);

  public List<Alerts> getAlertsForUser(long id);
}
