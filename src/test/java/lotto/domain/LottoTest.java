package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.domain.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Nested
    @DisplayName("실패 케이스")
    class Fail {

        @Test
        @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
        void overMaximumLottoCount() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .withMessage(ExceptionMessage.LOTTO_OVER_MAXIMUM_COUNT.getMessage());
        }

        @Test
        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
        void isDuplicate() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .withMessage(ExceptionMessage.LOTTO_IS_DUPLICATE.getMessage());
        }
    }
}
