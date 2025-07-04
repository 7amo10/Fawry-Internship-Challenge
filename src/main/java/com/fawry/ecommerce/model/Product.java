package com.fawry.ecommerce.model;

/**
 * Abstract base class for all products in the system.
 */
public abstract class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Decreases the product quantity by the specified amount.
     * @param amount The amount to decrease by
     * @throws IllegalArgumentException if amount is greater than available quantity
     */
    public void decreaseQuantity(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Cannot decrease quantity by more than available amount");
        }
        this.quantity -= amount;
    }

    /**
     * Checks if the product is available in the specified quantity.
     * @param requestedQuantity The quantity to check
     * @return true if the product is available in the requested quantity
     */
    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }

    /**
     * Checks if the product is expired.
     * @return true if the product is expired
     */
    public abstract boolean isExpired();

    /**
     * Checks if the product requires shipping.
     * @return true if the product requires shipping
     */
    public abstract boolean requiresShipping();
}