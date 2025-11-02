package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.domain.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {
    @Nested
    @DisplayName("실패 케이스")
    class Fail {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"  ", "\t"})
        @DisplayName("구입금액이 공백이면 예외가 발생한다")
        void isBlank(String rawPurchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchaseAmount(rawPurchaseAmount))
                    .withMessageContaining(ExceptionMessage.PURCHASE_AMOUNT_IS_EMPTY.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"삼천만원", "0.12345", "55 55 33","6000.0"})
        @DisplayName("구입금액이 정수가 아닐 경우 예외가 발생한다")
        void isNotDigit(String rawPurchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchaseAmount((rawPurchaseAmount)))
                    .withMessage(ExceptionMessage.PURCHASE_AMOUNT_IS_NOT_DIGIT.getMessage());
        }
    }
}
