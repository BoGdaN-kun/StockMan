package org.example.stockmannn;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockImportService {

    private final StockRepository stockRepository;

    public StockImportService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void importStocksFromCSV(String filepath) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            //skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String symbol = String.valueOf(data[0]);
                String name = String.valueOf(data[1]);
                Stock stock = new Stock();
                stock.setSymbol(symbol);
                stock.setName(name);
                stockRepository.save(stock);

            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

}
