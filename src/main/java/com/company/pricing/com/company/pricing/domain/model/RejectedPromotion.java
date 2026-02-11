package com.company.pricing.domain.model;

/**
 * Représente une promotion rejetée et la cause associée.
 */
public final class RejectedPromotion {

    private final String promotionId;
    private final String reason;

    public RejectedPromotion(String promotionId, String reason) {
        this.promotionId = requireText(promotionId, "promotionId");
        this.reason = requireText(reason, "reason");
    }

    public String promotionId() {
        return promotionId;
    }

    public String reason() {
        return reason;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }
}
