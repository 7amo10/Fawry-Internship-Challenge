package com.fawry.ecommerce.model;

/**
 * Interface for products that require shipping.
 * As per requirements, this interface only contains getName() and getWeight() methods.
 */
public interface Shippable {
    String getName();
    double getWeight(); // Weight in grams
}