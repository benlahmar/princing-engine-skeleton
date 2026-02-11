package com.company.pricing.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * Value Object représentant un montant monétaire immutable.
 */
public final class Money implements Comparable<Money> {

    private static final int SCALE = 2;

    private final BigDecimal amount;
    private final Currency currency;

    private Money(BigDecimal amount, Currency currency) {
        this.amount = normalize(amount);
        this.currency = Objects.requireNonNull(currency, "currency must not be null");
    }

    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(Objects.requireNonNull(amount, "amount must not be null"), currency);
    }

    public static Money zero(Currency currency) {
        return new Money(BigDecimal.ZERO, currency);
    }

    public BigDecimal amount() {
        return amount;
    }

    public Currency currency() {
        return currency;
    }

    public Money add(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money subtract(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    public Money multiply(BigDecimal factor) {
        return new Money(this.amount.multiply(Objects.requireNonNull(factor, "factor must not be null")), this.currency);
    }

    public boolean isNegative() {
        return amount.signum() < 0;
    }

    public boolean isZero() {
        return amount.signum() == 0;
    }

    @Override
    public int compareTo(Money other) {
        assertSameCurrency(other);
        return this.amount.compareTo(other.amount);
    }

    private void assertSameCurrency(Money other) {
        Objects.requireNonNull(other, "other money must not be null");
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch: %s != %s".formatted(this.currency, other.currency));
        }
    }

    private static BigDecimal normalize(BigDecimal amount) {
        return amount.setScale(SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return amount.equals(money.amount) && currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency.getCurrencyCode();
    }
}
