package com.stockalertingsystem.user_stock_service.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {
  @RequestMapping("/error")
  public String handleError(HttpServletRequest request) {
    // You can add custom logic here to handle the error and return a custom error page
    return "error"; // Assuming you have an error.html template in your resources/templates
    // directory
  }
}
