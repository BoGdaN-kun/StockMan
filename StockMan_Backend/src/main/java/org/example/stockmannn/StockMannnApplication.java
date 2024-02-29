package org.example.stockmannn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class StockMannnApplication implements CommandLineRunner {

    private final StockImportService stockImportService;
    private final StockHistoryCSVReader stockHistoryCSVReader;

    public StockMannnApplication(StockImportService stockImportService, StockHistoryCSVReader stockHistoryCSVReader) {
        this.stockImportService = stockImportService;
        this.stockHistoryCSVReader = stockHistoryCSVReader;
    }

//    public StockMannnApplication(StockImportService stockImportService) {
//        this.stockImportService = stockImportService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(StockMannnApplication.class, args);
    }

    //        @Override
//    public void run(String... args) throws Exception {
//        String filepath = "C:\\Users\\dudut\\Downloads\\nasdaq_screener_1709056251307.csv";
//        stockImportService.importStocksFromCSV(filepath);
//
//    }
    @Override
    public void run(String... args) throws Exception {
//        String filepath = "C:\\Users\\dudut\\Downloads\\archive\\full_history\\SM.csv";
//        stockHistoryCSVReader.readStocksHistoryFromCSV(filepath);
        //LocalDate startDate = LocalDate.parse("2018-07-30");
        //LocalDate endDate = LocalDate.parse("2018-08-07");
        //stockHistoryCSVReader.getStockHistoriesByDateBetween("SM", startDate, endDate);
        stockHistoryCSVReader.existsByStockSymbolAndDate("SM", LocalDate.parse("2018-08-07"));
        //  stockHistoryCSVReader.getStockHistoriesBySymbol("SM");
    }
}
