package lotto.application;

import lotto.application.provided.LottoOrder;
import lotto.domain.PurchasedAmount;

public class LottoOrderService implements LottoOrder {
    @Override
    public PurchasedAmount orderPurchaseAmount(String rawPurchaseAmount) {
        return new PurchasedAmount(rawPurchaseAmount);
    }
}
