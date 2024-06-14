package com.stockalertingsystem.user_stock_service.repository;

import com.stockalertingsystem.user_stock_service.model.Alerts;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertsRepository extends JpaRepository<Alerts, Long> {
  Optional<Alerts> findByUserId(long userId);

  Optional<Alerts> findByStockId(long stockId);
}
