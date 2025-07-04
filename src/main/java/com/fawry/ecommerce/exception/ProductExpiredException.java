package com.fawry.ecommerce.exception;

import com.fawry.ecommerce.model.Product;

/**
 * Exception thrown when a product is expired.
 */
public class ProductExpiredException extends Exception {
    private final Product product;

    public ProductExpiredException(Product product) {
        super(String.format("Product '%s' is expired", product.getName()));
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}