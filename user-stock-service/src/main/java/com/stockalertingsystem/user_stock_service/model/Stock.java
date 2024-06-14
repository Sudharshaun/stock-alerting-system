package com.stockalertingsystem.user_stock_service.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "stocks")
public class Stock {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "symbol")
  private String symbol;

  @Column(name = "name")
  private String name;

  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;

  @Column(name = "last_price")
  private BigDecimal lastPrice;
}
