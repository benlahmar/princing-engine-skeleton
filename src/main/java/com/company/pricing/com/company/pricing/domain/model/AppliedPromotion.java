package com.company.pricing.domain.model;

import java.util.Objects;

/**
 * Représente une promotion appliquée et son impact.
 */
public final class AppliedPromotion {

    private final String promotionId;
    private final String label;
    private final Money discount;

    public AppliedPromotion(String promotionId, String label, Money discount) {
        this.promotionId = requireText(promotionId, "promotionId");
        this.label = requireText(label, "label");
        this.discount = Objects.requireNonNull(discount, "discount must not be null");
        if (discount.isNegative()) {
            throw new IllegalArgumentException("discount must not be negative");
        }
    }

    public String promotionId() {
        return promotionId;
    }

    public String label() {
        return label;
    }

    public Money discount() {
        return discount;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }
}
