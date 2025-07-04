package com.fawry.ecommerce.model;

import java.time.LocalDate;

/**
 * Represents products that have an expiration date, like Cheese and Biscuits.
 */
public class ExpiringProduct extends Product {
    private LocalDate expirationDate;
    private boolean requiresShipping;
    private double weight; // in grams, only relevant if requiresShipping is true

    public ExpiringProduct(String name, double price, int quantity, LocalDate expirationDate, 
                          boolean requiresShipping, double weight) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
        this.requiresShipping = requiresShipping;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public boolean requiresShipping() {
        return requiresShipping;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public double getWeight() {
        return weight;
    }
}