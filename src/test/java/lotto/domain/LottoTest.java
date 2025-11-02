package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.domain.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Nested
    @DisplayName("성공 케이스")
    class Success {

        @Test
        @DisplayName("올바른 로또 번호가 오면 예외가 발생하지 않는다")
        void validLottoNumbers() {
            assertThatCode(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    class Fail {

        @Test
        @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
        void overMaximumLottoCount() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .withMessage(ExceptionMessage.LOTTO_INVALID_SIZE.getMessage());
        }

        @Test
        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
        void isDuplicate() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .withMessage(ExceptionMessage.LOTTO_IS_DUPLICATE.getMessage());
        }

        @Test
        @DisplayName("로또 번호가 (1~45)를 벗어날 경우 예외가 발생한다")
        void isInvalidRange() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                    .withMessage(ExceptionMessage.LOTTO_INVALID_RANGE.getMessage());

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                    .withMessage(ExceptionMessage.LOTTO_INVALID_RANGE.getMessage());
        }
    }
}
