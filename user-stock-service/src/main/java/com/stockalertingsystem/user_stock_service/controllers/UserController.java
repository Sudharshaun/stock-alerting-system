package com.stockalertingsystem.user_stock_service.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/csrf-token")
  public ResponseEntity<String> getCsrfToken(HttpServletRequest request) {
    CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    if (token != null) {
      return ResponseEntity.ok(token.getToken());
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Failed to retrieve CSRF token");
  }
}
