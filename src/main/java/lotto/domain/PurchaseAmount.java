package lotto.domain;

import java.math.BigInteger;

public class PurchaseAmount {
    private final BigInteger value;

    public PurchaseAmount(String rawPurchaseAmount) {
        this.value = new BigInteger(rawPurchaseAmount);
    }
}
