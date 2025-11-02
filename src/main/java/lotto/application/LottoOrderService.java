package lotto.application;

import lotto.application.provided.LottoOrder;
import lotto.domain.PurchaseAmount;

public class LottoOrderService implements LottoOrder {
    @Override
    public PurchaseAmount orderPurchaseAmount(String rawPurchaseAmount) {
        return new PurchaseAmount(rawPurchaseAmount);
    }
}
