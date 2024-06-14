package com.stockalertingsystem.user_stock_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private Long id;

  @Column(name = "email_id")
  private String email;

  @Column(name = "user_name")
  private String name;

  @Column(name = "provider")
  private AuthProvider provider;
}
