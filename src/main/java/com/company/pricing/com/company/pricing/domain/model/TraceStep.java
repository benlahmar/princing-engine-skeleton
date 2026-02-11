package com.company.pricing.domain.model;

import java.time.Instant;

/**
 * Evènement de trace métier.
 */
public final class TraceStep {

    private final Instant occurredAt;
    private final String action;
    private final String detail;

    public TraceStep(Instant occurredAt, String action, String detail) {
        this.occurredAt = occurredAt == null ? Instant.now() : occurredAt;
        this.action = requireText(action, "action");
        this.detail = requireText(detail, "detail");
    }

    public Instant occurredAt() {
        return occurredAt;
    }

    public String action() {
        return action;
    }

    public String detail() {
        return detail;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }
}
