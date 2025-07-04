package com.fawry.ecommerce.exception;

/**
 * Exception thrown when a customer has insufficient balance for checkout.
 */
public class InsufficientBalanceException extends Exception {
    private final double required;
    private final double available;

    public InsufficientBalanceException(double required, double available) {
        super(String.format("Insufficient balance: required %.2f, available %.2f", required, available));
        this.required = required;
        this.available = available;
    }

    public double getRequired() {
        return required;
    }

    public double getAvailable() {
        return available;
    }
}