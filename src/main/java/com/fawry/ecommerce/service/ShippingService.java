package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.Shippable;

import java.util.List;

/**
 * Service for handling shipping of products.
 */
public class ShippingService {
    // Shipping cost per gram (0.03 per 100g)
    private static final double SHIPPING_RATE = 0.0003;
    
    private Double fixedShippingCost = null;

    // Allow setting a fixed shipping cost for testing/requirements
    public void setFixedShippingCost(Double cost) {
        this.fixedShippingCost = cost;
    }

    public void clearFixedShippingCost() {
        this.fixedShippingCost = null;
    }

    /**
     * Ships the specified items.
     * @param items The items to ship
     * @return The shipping cost
     */
    public double ship(List<Shippable> items) {
        if (items == null || items.isEmpty()) {
            return 0;
        }
        double totalWeight = calculateTotalWeight(items);
        double shippingCost;
        if (fixedShippingCost != null) {
            shippingCost = fixedShippingCost;
        } else {
            shippingCost = calculateShippingCost(totalWeight);
        }
        // Print shipping details
        printShipmentNotice(items, totalWeight);
        return shippingCost;
    }
    
    /**
     * Calculates the total weight of all items.
     * @param items The items to calculate weight for
     * @return The total weight in grams
     */
    private double calculateTotalWeight(List<Shippable> items) {
        return items.stream()
                .mapToDouble(Shippable::getWeight)
                .sum();
    }
    
    /**
     * Calculates the shipping cost based on weight.
     * @param weightInGrams The weight in grams
     * @return The shipping cost
     */
    private double calculateShippingCost(double weightInGrams) {
        return Math.ceil(weightInGrams * SHIPPING_RATE);
    }
    
    /**
     * Prints the shipment notice.
     * @param items The items being shipped
     * @param totalWeight The total weight in grams
     */
    private void printShipmentNotice(List<Shippable> items, double totalWeight) {
        System.out.println("** Shipment notice **");
        
        for (Shippable item : items) {
            System.out.printf("%s\t%.0fg%n", item.getName(), item.getWeight());
        }
        
        // Convert grams to kg for display
        double weightInKg = totalWeight / 1000.0;
        System.out.printf("Total package weight %.1fkg%n", weightInKg);
        System.out.println();
    }
}