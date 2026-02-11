package com.company.pricing.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Trace fonctionnelle du calcul de pricing.
 */
public final class PricingTrace {

    private final List<TraceStep> steps;

    public PricingTrace(List<TraceStep> steps) {
        this.steps = List.copyOf(Objects.requireNonNull(steps, "steps must not be null"));
    }

    public static PricingTrace empty() {
        return new PricingTrace(List.of());
    }

    public List<TraceStep> steps() {
        return Collections.unmodifiableList(steps);
    }

    public PricingTrace append(TraceStep step) {
        Objects.requireNonNull(step, "step must not be null");
        List<TraceStep> newSteps = new java.util.ArrayList<>(steps);
        newSteps.add(step);
        return new PricingTrace(newSteps);
    }
}
