package lotto.application;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.PurchaseAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoOrderServiceTest {

    LottoOrderService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoOrderService();
    }

    @Test
    @DisplayName("올바른 구입금액이 입력되면 예외가 발생하지 않는다")
    void orderPurchaseAmount() {
        String rawPurchaseAmount = "8000";

        PurchaseAmount purchaseAmount = lottoService.orderPurchaseAmount(rawPurchaseAmount);

        assertThat(purchaseAmount).isNotNull();
    }
}
