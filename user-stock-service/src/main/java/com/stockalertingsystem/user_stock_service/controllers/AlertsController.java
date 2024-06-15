package com.stockalertingsystem.user_stock_service.controllers;

import com.stockalertingsystem.user_stock_service.dto.AlertsResponse;
import com.stockalertingsystem.user_stock_service.dto.SubscriptionRequest;
import com.stockalertingsystem.user_stock_service.model.Alerts;
import com.stockalertingsystem.user_stock_service.service.AlertService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscribe")
public class AlertsController {
  @Autowired private final AlertService alertService;

  public AlertsController(AlertService alertService) {
    this.alertService = alertService;
  }

  @PostMapping
  public ResponseEntity<AlertsResponse> addSubscriptions(
      @RequestBody SubscriptionRequest subscriptionRequest) {
    return ResponseEntity.accepted().body(alertService.add(subscriptionRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<List<Alerts>> getSubscriptions(@PathVariable long id) {
    return ResponseEntity.accepted().body(alertService.getAlertsForUser(id));
  }

  @PostMapping("/test")
  public String addDummy() {
    return "Added";
  }
}
