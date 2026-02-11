package com.company.pricing.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Agrégat panier.
 */
public final class Basket {

    private final List<BasketLine> lines;

    public Basket(List<BasketLine> lines) {
        List<BasketLine> safeLines = List.copyOf(Objects.requireNonNull(lines, "lines must not be null"));
        if (safeLines.isEmpty()) {
            throw new IllegalArgumentException("basket must contain at least one line");
        }
        this.lines = safeLines;
    }

    public List<BasketLine> lines() {
        return Collections.unmodifiableList(lines);
    }

    public Money subtotal() {
        Money first = lines.get(0).lineTotal();
        Money total = Money.zero(first.currency());
        for (BasketLine line : lines) {
            total = total.add(line.lineTotal());
        }
        return total;
    }

    public int totalQuantity() {
        return lines.stream().mapToInt(BasketLine::quantity).sum();
    }
}
