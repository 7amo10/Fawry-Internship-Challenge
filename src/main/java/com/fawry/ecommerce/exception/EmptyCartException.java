package com.fawry.ecommerce.exception;

/**
 * Exception thrown when attempting to checkout with an empty cart.
 */
public class EmptyCartException extends Exception {
    public EmptyCartException() {
        super("Cannot checkout with an empty cart");
    }
}