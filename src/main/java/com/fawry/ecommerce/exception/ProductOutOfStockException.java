package com.fawry.ecommerce.exception;

import com.fawry.ecommerce.model.Product;

/**
 * Exception thrown when a product is out of stock.
 */
public class ProductOutOfStockException extends Exception {
    private final Product product;
    private final int requestedQuantity;

    public ProductOutOfStockException(Product product, int requestedQuantity) {
        super(String.format("Product '%s' is out of stock: requested %d, available %d", 
                product.getName(), requestedQuantity, product.getQuantity()));
        this.product = product;
        this.requestedQuantity = requestedQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }
}