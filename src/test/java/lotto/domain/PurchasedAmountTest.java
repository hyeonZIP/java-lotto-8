package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.domain.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PurchasedAmountTest {
    @Nested
    @DisplayName("성공 케이스")
    class Success {

        @ParameterizedTest
        @ValueSource(strings = {"1000", "10000", "12345000", "100000000"})
        @DisplayName("올바른 구입금액이 입력되면 예외가 발생하지 않는다")
        void orderPurchaseAmount(String rawPurchaseAmount) {
            assertThatCode(() -> new PurchasedAmount(rawPurchaseAmount))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    class Fail {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"  ", "\t"})
        @DisplayName("구입금액이 공백이면 예외가 발생한다")
        void isBlank(String rawPurchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchasedAmount(rawPurchaseAmount))
                    .withMessageContaining(ExceptionMessage.PURCHASE_AMOUNT_IS_EMPTY.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"삼천만원", "0.12345", "55 55 33", "6000.0"})
        @DisplayName("구입금액이 정수가 아닐 경우 예외가 발생한다")
        void isNotDigit(String rawPurchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchasedAmount((rawPurchaseAmount)))
                    .withMessage(ExceptionMessage.PURCHASE_AMOUNT_IS_NOT_DIGIT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "-0", "-1", "-8000"})
        @DisplayName("구입금액이 양수가 아닐 경우 예외가 발생한다")
        void isNotPositive(String rawPurchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchasedAmount((rawPurchaseAmount)))
                    .withMessage(ExceptionMessage.PURCHASE_AMOUNT_IS_NOT_POSITIVE.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1001", "999", "1", "10009"})
        @DisplayName("구입금액이 로또 1장 가격(1,000)으로 나누어 떨어지지 않을 경우 예외가 발생한다")
        void isIndivisible(String rawPurchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchasedAmount((rawPurchaseAmount)))
                    .withMessage(ExceptionMessage.PURCHASE_AMOUNT_IS_INDIVISIBLE.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"200000000", "1000000000000000000"})
        @DisplayName("구임금액이 1억원을 넘을 경우 예외가 발생한다")
        void isOverOneBillion(String rawPurchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchasedAmount((rawPurchaseAmount)))
                    .withMessage(ExceptionMessage.PURCHASE_AMOUNT_IS_OVER_MAXIMUM.getMessage());
        }
    }
}
