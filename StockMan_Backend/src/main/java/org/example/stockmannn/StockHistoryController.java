package org.example.stockmannn;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class StockHistoryController {

    private final StockHistoryRepository stockHistoryRepository;

    public StockHistoryController(StockHistoryRepository stockHistoryRepository) {
        this.stockHistoryRepository = stockHistoryRepository;
    }

        @GetMapping("/stockHistories")
    public List<StockHistory> findAll() {
        return stockHistoryRepository.findAll();
    }

    @GetMapping("/stockHistories/{symbol}")
    public List<StockHistory> findbySymbol(@PathVariable("symbol") String symbol) {
        return stockHistoryRepository.findAllByStockSymbol(symbol);
    }
    
    @GetMapping("/stockHistories/{symbol}/{startDate}/{endDate}")
    public List<StockHistory> findbySymbolAndDateBetween(@PathVariable("symbol") String symbol, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return stockHistoryRepository.findAllByStockSymbolAndDateBetween(symbol, start, end);
    }


}
