package com.company.pricing.domain.model;

import java.util.Objects;

/**
 * Entité métier produit.
 */
public final class Product {

    private final String id;
    private final String name;
    private final String category;
    private final Money unitPrice;

    public Product(String id, String name, String category, Money unitPrice) {
        this.id = requireText(id, "id");
        this.name = requireText(name, "name");
        this.category = requireText(category, "category");
        this.unitPrice = Objects.requireNonNull(unitPrice, "unitPrice must not be null");
        if (unitPrice.isNegative()) {
            throw new IllegalArgumentException("unitPrice must not be negative");
        }
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String category() {
        return category;
    }

    public Money unitPrice() {
        return unitPrice;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }
}
