package lotto.application.provided;

import lotto.domain.PurchasedAmount;

/**
 * 로또 발급이전의 주문 기능을 제공한다
 */
public interface LottoOrder {
    PurchasedAmount orderPurchaseAmount(String rawPurchaseAmount);
}
