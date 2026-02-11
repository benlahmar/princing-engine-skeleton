package com.company.pricing.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entité promotion: porte uniquement les règles de calcul de remise.
 */
public final class Promotion {

    private final String id;
    private final String label;
    private final PromotionScope scope;
    private final PromotionCumulRule cumulRule;
    private final int priority;
    private final BigDecimal discountRate;

    public Promotion(String id,
                     String label,
                     PromotionScope scope,
                     PromotionCumulRule cumulRule,
                     int priority,
                     BigDecimal discountRate) {
        this.id = requireText(id, "id");
        this.label = requireText(label, "label");
        this.scope = Objects.requireNonNull(scope, "scope must not be null");
        this.cumulRule = Objects.requireNonNull(cumulRule, "cumulRule must not be null");
        if (priority < 0) {
            throw new IllegalArgumentException("priority must be >= 0");
        }
        this.priority = priority;
        this.discountRate = normalizeRate(discountRate);
    }

    public String id() {
        return id;
    }

    public String label() {
        return label;
    }

    public PromotionScope scope() {
        return scope;
    }

    public PromotionCumulRule cumulRule() {
        return cumulRule;
    }

    public int priority() {
        return priority;
    }

    public BigDecimal discountRate() {
        return discountRate;
    }

    public Money computeDiscount(Money baseAmount) {
        Objects.requireNonNull(baseAmount, "baseAmount must not be null");
        if (baseAmount.isNegative()) {
            throw new IllegalArgumentException("baseAmount must not be negative");
        }
        return baseAmount.multiply(discountRate);
    }

    private static BigDecimal normalizeRate(BigDecimal rate) {
        Objects.requireNonNull(rate, "discountRate must not be null");
        if (rate.compareTo(BigDecimal.ZERO) < 0 || rate.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("discountRate must be between 0 and 1");
        }
        return rate;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }
}
