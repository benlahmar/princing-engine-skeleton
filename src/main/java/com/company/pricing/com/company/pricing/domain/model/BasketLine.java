package com.company.pricing.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Ligne de panier immutable.
 */
public final class BasketLine {

    private final Product product;
    private final int quantity;

    public BasketLine(Product product, int quantity) {
        this.product = Objects.requireNonNull(product, "product must not be null");
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }
        this.quantity = quantity;
    }

    public Product product() {
        return product;
    }

    public int quantity() {
        return quantity;
    }

    public Money lineTotal() {
        return product.unitPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
