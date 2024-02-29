package org.example.stockmannn;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class StockHistory {

    @Id
    @GeneratedValue
    private Long Id;
    private LocalDate date;
    private Double open;
    private Double close;
    private Double change;
    private Double percentChange;

    private Double adjustedClose;
    private Double volume;
    private Double high;
    private Double low;

    @ManyToOne
    @JoinColumn(name = "symbol_id")
    @JsonBackReference
    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public StockHistory() {
    }

    public Long getStockId() {
        return (stock != null) ? stock.getId() : null;
    }

    public StockHistory(LocalDate date, Double open, Double close, Double adjustedClose, Double volume, Double high, Double low) {
        this.date = date;
        this.open = open;
        this.close = close;
        this.change = close - open;
        this.percentChange = (close / open) * 100;
        this.adjustedClose = adjustedClose;
        this.volume = volume;
        this.high = high;
        this.low = low;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getChange() {
        return this.close - this.open;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getPercentChange() {
        return (this.close / this.open) * 100;
    }

    public void setPercentChange(Double percentChange) {
        this.percentChange = percentChange;
    }

    public Double getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(Double adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }
}
