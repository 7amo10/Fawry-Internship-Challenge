package com.fawry.ecommerce.model;

/**
 * Represents products that do not expire, like TV and Mobile.
 */
public class NonExpiringProduct extends Product {
    private boolean requiresShipping;
    private double weight; // in grams, only relevant if requiresShipping is true

    public NonExpiringProduct(String name, double price, int quantity, 
                             boolean requiresShipping, double weight) {
        super(name, price, quantity);
        this.requiresShipping = requiresShipping;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return false; // Non-expiring products never expire
    }

    @Override
    public boolean requiresShipping() {
        return requiresShipping;
    }

    public double getWeight() {
        return weight;
    }
}