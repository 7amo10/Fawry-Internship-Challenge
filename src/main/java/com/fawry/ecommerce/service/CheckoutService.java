package com.fawry.ecommerce.service;

import com.fawry.ecommerce.exception.EmptyCartException;
import com.fawry.ecommerce.exception.InsufficientBalanceException;
import com.fawry.ecommerce.exception.ProductExpiredException;
import com.fawry.ecommerce.exception.ProductOutOfStockException;
import com.fawry.ecommerce.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for handling checkout process.
 */
public class CheckoutService {
    private final ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    /**
     * Processes checkout for a customer with the specified cart.
     * @param customer The customer
     * @param cart The cart
     * @throws EmptyCartException if the cart is empty
     * @throws ProductOutOfStockException if a product is out of stock
     * @throws ProductExpiredException if a product is expired
     * @throws InsufficientBalanceException if the customer has insufficient balance
     */
    public void checkout(Customer customer, Cart cart) throws EmptyCartException, 
            ProductOutOfStockException, ProductExpiredException, InsufficientBalanceException {
        
        // Check if cart is empty
        if (cart.isEmpty()) {
            throw new EmptyCartException();
        }
        
        // Validate products
        validateProducts(cart);
        
        // Calculate subtotal
        double subtotal = cart.getSubtotal();
        
        // Process shipping for applicable items
        List<Shippable> shippableItems = getShippableItems(cart);
        double shippingCost = shippingService.ship(shippableItems);
        
        // Calculate total amount
        double totalAmount = subtotal + shippingCost;
        
        // Check if customer has sufficient balance
        if (!customer.hasSufficientBalance(totalAmount)) {
            throw new InsufficientBalanceException(totalAmount, customer.getBalance());
        }
        
        // Process payment
        customer.deductBalance(totalAmount);
        
        // Update product quantities
        updateProductQuantities(cart);
        
        // Print receipt
        printReceipt(cart, subtotal, shippingCost, totalAmount, customer);
    }
    
    /**
     * Validates all products in the cart.
     * @param cart The cart
     * @throws ProductOutOfStockException if a product is out of stock
     * @throws ProductExpiredException if a product is expired
     */
    private void validateProducts(Cart cart) throws ProductOutOfStockException, ProductExpiredException {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            
            // Check if product is expired
            if (product.isExpired()) {
                throw new ProductExpiredException(product);
            }
            
            // Check if product is available in the requested quantity
            if (!product.isAvailable(quantity)) {
                throw new ProductOutOfStockException(product, quantity);
            }
        }
    }
    
    /**
     * Gets all shippable items from the cart.
     * @param cart The cart
     * @return List of shippable items
     */
    private List<Shippable> getShippableItems(Cart cart) {
        List<Shippable> shippableItems = new ArrayList<>();
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            
            if (product.requiresShipping()) {
                shippableItems.add(new ShippableProduct(product, item.getQuantity()));
            }
        }
        
        return shippableItems;
    }
    
    /**
     * Updates product quantities after checkout.
     * @param cart The cart
     */
    private void updateProductQuantities(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.decreaseQuantity(item.getQuantity());
        }
    }
    
    /**
     * Prints the checkout receipt.
     * @param cart The cart
     * @param subtotal The subtotal
     * @param shippingCost The shipping cost
     * @param totalAmount The total amount
     * @param customer The customer
     */
    private void printReceipt(Cart cart, double subtotal, double shippingCost, 
                             double totalAmount, Customer customer) {
        System.out.println("** Checkout receipt **");
        
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s\t%.0f%n", 
                    item.getQuantity(), 
                    item.getProduct().getName(), 
                    item.getSubtotal());
        }
        
        System.out.println("----------------------");
        System.out.printf("Subtotal\t%.0f%n", subtotal);
        System.out.printf("Shipping\t%.0f%n", shippingCost);
        System.out.printf("Amount\t\t%.0f%n", totalAmount);
        System.out.printf("Current Balance\t%.0f%n", customer.getBalance());
        System.out.println();
    }
}