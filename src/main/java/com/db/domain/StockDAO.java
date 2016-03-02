package com.db.domain;

public class StockDAO {
    public double placeOrder(String tickerId, int amount, int operation) {
        if(amount < 0) throw new IllegalArgumentException("Below zero amount");

        double result;
        switch (operation) {
            case 1: result = buy(tickerId, amount); break;
            case 2: result = sell(tickerId, amount); break;
            default: throw new IllegalArgumentException("Unsupported operation " + operation);
        }

        return result;
    }

    private int sell(String tickerId, int amount) {
        return 1/amount;
    }

    private double buy(String tickerId, int amount) {
        return 1.0/amount;
     }
}
