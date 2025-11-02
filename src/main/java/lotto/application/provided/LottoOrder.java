package lotto.application.provided;

import lotto.domain.PurchaseAmount;

/**
 * 로또 발급이전의 주문 기능을 제공한다
 */
public interface LottoOrder {
    PurchaseAmount orderPurchaseAmount(String rawPurchaseAmount);
}
