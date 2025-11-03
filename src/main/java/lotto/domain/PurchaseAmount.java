package lotto.domain;

import java.math.BigInteger;
import lotto.domain.exception.ExceptionMessage;

public class PurchaseAmount {
    private static final BigInteger LOTTO_PRICE = new BigInteger("1000");
    private static final BigInteger MAXIMUM_PURCHASE_AMOUNT = new BigInteger("100000000");

    private final BigInteger value;

    public PurchaseAmount(String rawPurchaseAmount) {
        this.value = parseAndValidate(rawPurchaseAmount);
    }

    public int calculateLottoCount() {
        return value.divide(LOTTO_PRICE).intValueExact();
    }

    public int getAmount() {
        return value.intValueExact();
    }

    private BigInteger parseAndValidate(String rawPurchaseAmount) {
        validateEmpty(rawPurchaseAmount);

        BigInteger purchaseAmount = parse(rawPurchaseAmount);

        validatePositive(purchaseAmount);
        validateDivisible(purchaseAmount);
        validateMaximum(purchaseAmount);

        return purchaseAmount;
    }

    private void validateMaximum(BigInteger purchaseAmount) {
        if (isBiggerThanMaximum(purchaseAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_OVER_MAXIMUM.getMessage());
        }
    }

    private boolean isBiggerThanMaximum(BigInteger purchaseAmount) {
        return purchaseAmount.compareTo(MAXIMUM_PURCHASE_AMOUNT) > 0;
    }

    private BigInteger parse(String rawPurchaseAmount) {
        try {
            return new BigInteger(rawPurchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_NOT_DIGIT.getMessage());
        }
    }

    private void validateEmpty(String rawPurchaseAmount) {
        if (rawPurchaseAmount == null || rawPurchaseAmount.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_EMPTY.getMessage());
        }
    }

    private void validatePositive(BigInteger purchaseAmount) {
        if (isNegative(purchaseAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_NOT_POSITIVE.getMessage());
        }
    }

    private boolean isNegative(BigInteger purchaseAmount) {
        return purchaseAmount.compareTo(BigInteger.ZERO) <= 0;
    }

    private void validateDivisible(BigInteger purchaseAmount) {
        if (isIndivisible(purchaseAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_INDIVISIBLE.getMessage());
        }
    }

    private boolean isIndivisible(BigInteger purchaseAmount) {
        return !purchaseAmount.mod(LOTTO_PRICE).equals(BigInteger.ZERO);
    }
}
