package lotto.domain;

import java.math.BigInteger;
import lotto.domain.exception.ExceptionMessage;

public class PurchaseAmount {
    private final BigInteger value;

    public PurchaseAmount(String rawPurchaseAmount) {
        validate(rawPurchaseAmount);
        this.value = new BigInteger(rawPurchaseAmount);
    }

    private void validate(String rawPurchaseAmount) {
        validateEmpty(rawPurchaseAmount);
        validateDigit(rawPurchaseAmount);
        validatePositive(rawPurchaseAmount);
    }

    private void validateEmpty(String rawPurchaseAmount) {
        if (rawPurchaseAmount == null || rawPurchaseAmount.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_EMPTY.getMessage());
        }
    }

    private void validateDigit(String rawPurchaseAmount) {
        try {
            new BigInteger(rawPurchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_NOT_DIGIT.getMessage());
        }
    }

    private void validatePositive(String rawPurchaseAmount) {
        BigInteger purchaseAmount = new BigInteger(rawPurchaseAmount);

        if (isNegative(purchaseAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_NOT_POSITIVE.getMessage());
        }
    }

    private boolean isNegative(BigInteger purchaseAmount) {
        return purchaseAmount.compareTo(BigInteger.ZERO) <= 0;
    }
}
