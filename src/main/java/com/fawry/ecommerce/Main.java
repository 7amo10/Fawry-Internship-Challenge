package com.fawry.ecommerce;

import com.fawry.ecommerce.exception.EmptyCartException;
import com.fawry.ecommerce.exception.InsufficientBalanceException;
import com.fawry.ecommerce.exception.ProductExpiredException;
import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.service.CheckoutService;
import com.fawry.ecommerce.service.ShippingService;

import java.time.LocalDate;

/**
 * Main class to demonstrate the e-commerce system functionality.
 */
public class Main {
    public static void main(String[] args) {
        // Create products
        ExpiringProduct cheese = new ExpiringProduct("Cheese", 100, 10, LocalDate.now().plusDays(30), true, 200);
        ExpiringProduct biscuits = new ExpiringProduct("Biscuits", 150, 5, LocalDate.now().plusDays(90), true, 700);
        NonExpiringProduct tv = new NonExpiringProduct("TV", 5000, 3, true, 8000);
        NonExpiringProduct scratchCard = new NonExpiringProduct("Scratch Card", 50, 100, false, 0);
        Customer customer = new Customer("John Doe", 10000);
        Cart cart = new Cart();
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);
        try {
            // Scenario 1: Successful checkout with cheese, biscuits, and scratch card
            System.out.println("=== Scenario 1: Successful checkout with cheese, biscuits, and scratch card ===");
            shippingService.setFixedShippingCost(30.0);
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);
            checkoutService.checkout(customer, cart);
            shippingService.clearFixedShippingCost();
            cart.clear();
            // Scenario 2: Checkout with expired product
            System.out.println("=== Scenario 2: Checkout with expired product ===");
            ExpiringProduct expiredCheese = new ExpiringProduct("Expired Cheese", 100, 5, LocalDate.now().minusDays(1), true, 200);
            cart.add(expiredCheese, 1);
            try {
                checkoutService.checkout(customer, cart);
            } catch (ProductExpiredException e) {
                System.out.println("Error: " + e.getMessage());
            }
            cart.clear();
            // Scenario 3: Checkout with insufficient balance
            System.out.println("=== Scenario 3: Checkout with insufficient balance ===");
            NonExpiringProduct expensiveTV = new NonExpiringProduct("Expensive TV", 20000, 1, true, 10000);
            cart.add(expensiveTV, 1);
            try {
                checkoutService.checkout(customer, cart);
            } catch (InsufficientBalanceException e) {
                System.out.println("Error: " + e.getMessage());
            }
            cart.clear();
            // Scenario 4: Checkout with empty cart
            System.out.println("=== Scenario 4: Checkout with empty cart ===");
            try {
                checkoutService.checkout(customer, cart);
            } catch (EmptyCartException e) {
                System.out.println("Error: " + e.getMessage());
            }
            // Scenario 5: Checkout with quantity exceeding stock
            System.out.println("=== Scenario 5: Checkout with quantity exceeding stock ===");
            try {
                cart.add(tv, 10);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}