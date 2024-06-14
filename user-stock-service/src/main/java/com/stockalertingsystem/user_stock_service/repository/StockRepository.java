package com.stockalertingsystem.user_stock_service.repository;

import com.stockalertingsystem.user_stock_service.model.Stock;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
  Optional<Stock> findById(long id);

  Optional<Stock> findBySymbol(String symbol);
}
