package com.fawry.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart in the e-commerce system.
 */
public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a product to the cart with the specified quantity.
     * @param product The product to add
     * @param quantity The quantity to add
     * @throws IllegalArgumentException if quantity is greater than available product quantity
     */
    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Not enough quantity available for " + product.getName());
        }
        
        // Check if product already exists in cart
        for (CartItem item : items) {
            if (item.getProduct() == product) {
                // Product already in cart, just update quantity
                int newQuantity = item.getQuantity() + quantity;
                if (!product.isAvailable(newQuantity)) {
                    throw new IllegalArgumentException("Not enough quantity available for " + product.getName());
                }
                items.remove(item);
                items.add(new CartItem(product, newQuantity));
                return;
            }
        }
        
        // Product not in cart, add new item
        items.add(new CartItem(product, quantity));
    }

    /**
     * Removes a product from the cart.
     * @param product The product to remove
     */
    public void remove(Product product) {
        items.removeIf(item -> item.getProduct() == product);
    }

    /**
     * Clears the cart.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Gets all items in the cart.
     * @return List of cart items
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Checks if the cart is empty.
     * @return true if the cart is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Calculates the subtotal of all items in the cart.
     * @return The subtotal
     */
    public double getSubtotal() {
        return items.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }
}