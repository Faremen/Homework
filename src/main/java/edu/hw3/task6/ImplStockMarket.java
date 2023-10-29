package edu.hw3.task6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class ImplStockMarket implements StockMarket {

    private final PriorityQueue<Stock> stocks = new PriorityQueue<>(new StockComparator());

    @Override
    public void add(Stock stock) {
        Objects.requireNonNull(stock);

        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }

    public ArrayList<Stock> getStocks() {
        return new ArrayList<>(stocks);
    }
}
