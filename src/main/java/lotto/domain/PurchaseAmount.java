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
    }

    private void validateEmpty(String rawPurchaseAmount) {
        if (rawPurchaseAmount == null || rawPurchaseAmount.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_EMPTY.getMessage());
        }
    }
}
