package com.fawry.ecommerce.model;

/**
 * Adapter class that wraps a Product and implements the Shippable interface.
 * This is used to pass products to the ShippingService.
 */
public class ShippableProduct implements Shippable {
    private final Product product;
    private final int quantity;

    public ShippableProduct(Product product, int quantity) {
        if (!product.requiresShipping()) {
            throw new IllegalArgumentException("Product does not require shipping");
        }
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return quantity + "x " + product.getName();
    }

    @Override
    public double getWeight() {
        if (product instanceof ExpiringProduct) {
            return ((ExpiringProduct) product).getWeight() * quantity;
        } else if (product instanceof NonExpiringProduct) {
            return ((NonExpiringProduct) product).getWeight() * quantity;
        }
        return 0; // Should never happen if validation is correct
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}