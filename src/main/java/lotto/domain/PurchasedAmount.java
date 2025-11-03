package lotto.domain;

import java.math.BigInteger;
import lotto.domain.exception.ExceptionMessage;

public class PurchasedAmount {
    private static final BigInteger LOTTO_PRICE = new BigInteger("1000");
    private static final BigInteger MAXIMUM_PURCHASE_AMOUNT = new BigInteger("100000000");

    private final BigInteger value;

    public PurchasedAmount(BigInteger purchasedAmount) {
        this.value = purchasedAmount;
    }

    public static PurchasedAmount of(String rawPurchasedAmount){
        return new PurchasedAmount(parseAndValidate(rawPurchasedAmount));
    }

    public int calculateLottoCount() {
        return value.divide(LOTTO_PRICE).intValueExact();
    }

    public int getAmount() {
        return value.intValueExact();
    }

    private static BigInteger parseAndValidate(String rawPurchaseAmount) {
        validateEmpty(rawPurchaseAmount);

        BigInteger purchaseAmount = parse(rawPurchaseAmount);

        validatePositive(purchaseAmount);
        validateDivisible(purchaseAmount);
        validateMaximum(purchaseAmount);

        return purchaseAmount;
    }

    private static void validateMaximum(BigInteger purchaseAmount) {
        if (isBiggerThanMaximum(purchaseAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_OVER_MAXIMUM.getMessage());
        }
    }

    private static boolean isBiggerThanMaximum(BigInteger purchaseAmount) {
        return purchaseAmount.compareTo(MAXIMUM_PURCHASE_AMOUNT) > 0;
    }

    private static BigInteger parse(String rawPurchaseAmount) {
        try {
            return new BigInteger(rawPurchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_NOT_DIGIT.getMessage());
        }
    }

    private static void validateEmpty(String rawPurchaseAmount) {
        if (rawPurchaseAmount == null || rawPurchaseAmount.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_EMPTY.getMessage());
        }
    }

    private static void validatePositive(BigInteger purchaseAmount) {
        if (isNegative(purchaseAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_NOT_POSITIVE.getMessage());
        }
    }

    private static boolean isNegative(BigInteger purchaseAmount) {
        return purchaseAmount.compareTo(BigInteger.ZERO) <= 0;
    }

    private static void validateDivisible(BigInteger purchaseAmount) {
        if (isIndivisible(purchaseAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_IS_INDIVISIBLE.getMessage());
        }
    }

    private static boolean isIndivisible(BigInteger purchaseAmount) {
        return !purchaseAmount.mod(LOTTO_PRICE).equals(BigInteger.ZERO);
    }
}
