package org.example.stockmannn;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Stock {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(unique = true)
    private String symbol;
    private String name;


    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<StockHistory> stockHistories;

    public Stock() {
    }

    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public List<StockHistory> getStockHistories() {
        return stockHistories;
    }

    public void setStockHistories(List<StockHistory> stockHistories) {
        this.stockHistories = stockHistories;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
