package org.example.stockmannn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findBySymbol(String symbol);
}
