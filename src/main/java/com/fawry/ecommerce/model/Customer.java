package com.fawry.ecommerce.model;

/**
 * Represents a customer in the e-commerce system.
 */
public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Deducts the specified amount from the customer's balance.
     * @param amount The amount to deduct
     * @throws IllegalArgumentException if amount is greater than balance
     */
    public void deductBalance(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
    }

    /**
     * Checks if the customer has sufficient balance for the specified amount.
     * @param amount The amount to check
     * @return true if the customer has sufficient balance
     */
    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }
}