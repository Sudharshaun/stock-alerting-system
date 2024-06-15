package com.stockalertingsystem.user_stock_service.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Data
@Table(name = "alerts")
public class Alerts {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "stock_id")
  private Stock stock;

  @Column(name = "condition_type")
  private String conditionType;

  @Column(name = "threshold")
  private BigDecimal threshold;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;
}
