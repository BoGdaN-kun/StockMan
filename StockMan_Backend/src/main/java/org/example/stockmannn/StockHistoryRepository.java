package org.example.stockmannn;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Stack;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

    StockHistory findAllByStock(Stock stock);

    StockHistory findByStock(Stock stock);

    List<StockHistory> findAllByStockSymbol(String symbol);

    List<StockHistory> findAllByStockSymbolAndDateBetween(String symbol, LocalDate startDate, LocalDate endDate);

    Boolean existsByStockSymbolAndDate(String symbol, LocalDate date);


}
