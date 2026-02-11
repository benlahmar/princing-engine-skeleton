package com.company.pricing.domain.model;

import java.util.Optional;

/**
 * Résultat d'éligibilité d'une promotion.
 */
public final class EligibilityResult {

    private final boolean eligible;
    private final String reason;

    private EligibilityResult(boolean eligible, String reason) {
        this.eligible = eligible;
        this.reason = reason;
    }

    public static EligibilityResult eligible() {
        return new EligibilityResult(true, null);
    }

    public static EligibilityResult ineligible(String reason) {
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("reason must not be blank");
        }
        return new EligibilityResult(false, reason);
    }

    public boolean isEligible() {
        return eligible;
    }

    public Optional<String> reason() {
        return Optional.ofNullable(reason);
    }
}
