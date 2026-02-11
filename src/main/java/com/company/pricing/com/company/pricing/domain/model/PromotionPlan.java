package com.company.pricing.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Résultat métier d'application des promotions.
 */
public final class PromotionPlan {

    private final Money subtotal;
    private final List<AppliedPromotion> appliedPromotions;
    private final List<RejectedPromotion> rejectedPromotions;
    private final PricingTrace pricingTrace;

    public PromotionPlan(Money subtotal,
                         List<AppliedPromotion> appliedPromotions,
                         List<RejectedPromotion> rejectedPromotions,
                         PricingTrace pricingTrace) {
        this.subtotal = Objects.requireNonNull(subtotal, "subtotal must not be null");
        this.appliedPromotions = List.copyOf(Objects.requireNonNull(appliedPromotions, "appliedPromotions must not be null"));
        this.rejectedPromotions = List.copyOf(Objects.requireNonNull(rejectedPromotions, "rejectedPromotions must not be null"));
        this.pricingTrace = Objects.requireNonNull(pricingTrace, "pricingTrace must not be null");
    }

    public static PromotionPlan emptyFor(Money subtotal) {
        return new PromotionPlan(subtotal, List.of(), List.of(), PricingTrace.empty());
    }

    public Money subtotal() {
        return subtotal;
    }

    public List<AppliedPromotion> appliedPromotions() {
        return Collections.unmodifiableList(appliedPromotions);
    }

    public List<RejectedPromotion> rejectedPromotions() {
        return Collections.unmodifiableList(rejectedPromotions);
    }

    public PricingTrace pricingTrace() {
        return pricingTrace;
    }

    public Money totalDiscount() {
        Money total = Money.zero(subtotal.currency());
        for (AppliedPromotion promotion : appliedPromotions) {
            total = total.add(promotion.discount());
        }
        return total;
    }

    public Money finalPrice() {
        Money result = subtotal.subtract(totalDiscount());
        return result.isNegative() ? Money.zero(subtotal.currency()) : result;
    }
}
