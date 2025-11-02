package lotto.application.provided;

import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;

/**
 * 구매 금액만큼 로또를 발급한다
 */
public interface LottoDispenser {
    Lottos dispenseLottos(PurchaseAmount purchaseAmount);
}
