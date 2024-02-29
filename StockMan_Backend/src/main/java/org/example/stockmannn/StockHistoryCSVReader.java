package org.example.stockmannn;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockHistoryCSVReader {

    private final StockHistoryRepository stockHistoryRepository;
    private final StockRepository stockRepository;

    public StockHistoryCSVReader(StockHistoryRepository stockHistoryRepository, StockRepository stockRepository) {
        this.stockHistoryRepository = stockHistoryRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void readStocksHistoryFromCSV(String filepath) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            Path path = Paths.get(filepath);
            String ticker = path.getFileName().toString().replace(".csv", "");


            //skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                LocalDate date = LocalDate.parse(data[0]);
                Double volume = Double.valueOf(data[1]);
                Double open = Double.valueOf(data[2]);
                Double high = Double.valueOf(data[3]);
                Double low = Double.valueOf(data[4]);
                Double close = Double.valueOf(data[5]);
                Double adjClose = Double.valueOf(data[6]);
                StockHistory stockHistory = new StockHistory();
                stockHistory.setDate(date);
                stockHistory.setVolume(volume);
                stockHistory.setOpen(open);
                stockHistory.setHigh(high);
                stockHistory.setLow(low);
                stockHistory.setClose(close);
                stockHistory.setAdjustedClose(adjClose);
                stockHistory.setChange(close - open);
                stockHistory.setPercentChange((close / open) * 100);


                Stock stock = stockRepository.findBySymbol(ticker);

                stockHistory.setStock(stock);
                stockHistoryRepository.save(stockHistory);

            }

        }
    }

    public List<StockHistory> getStockHistoriesBySymbol(String symbol) {
        return stockHistoryRepository.findAllByStockSymbol(symbol);
    }


    public List<StockHistory> getStockHistoriesByDateBetween(String symbol, LocalDate date1, LocalDate date2) {
        return stockHistoryRepository.findAllByStockSymbolAndDateBetween(symbol, date1, date2);
    }

    public boolean existsByStockSymbolAndDate(String symbol, LocalDate date) {
        return stockHistoryRepository.existsByStockSymbolAndDate(symbol, date);
    }

}
