package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.application.required.WinningNumberExtractor;
import lotto.domain.exception.ExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {
    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = WinningLotto.of("1,2,3,4,5,6",
                mockWinningNumberExtractor(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Nested
    @DisplayName("성공 케이스")
    class Success {

        @Test
        @DisplayName("올바른 보너스 번호를 입력하면 예외가 발생하지 않는다")
        void registerBonusNumber() {
            String rawBonusNumber = "7";

            assertThatCode(() -> BonusNumber.register(rawBonusNumber, winningLotto))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    class Fail {

        @Test
        @DisplayName("보너스 번호가 공백이면 예외가 발생한다")
        void isBlank() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> BonusNumber.register("", winningLotto))
                    .withMessage(ExceptionMessage.BONUS_NUMBER_IS_BLANK.getMessage());
        }

        @Test
        @DisplayName("보너스 번호가 정수가 아닐 경우 예외가 발생한다")
        void isNotDigit() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> BonusNumber.register("칠", winningLotto))
                    .withMessage(ExceptionMessage.BONUS_NUMBER_IS_NOT_DIGIT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "46"})
        @DisplayName("보너스 번호가 로또 숫자 범위의 숫자가 아닐 경우 예외가 발생한다")
        void isOutOfLottoNumberRange(String bonusNumber) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> BonusNumber.register(bonusNumber, winningLotto))
                    .withMessage(ExceptionMessage.BONUS_NUMBER_IS_OUT_OF_RANGE.getMessage());
        }

        @Test
        @DisplayName("보너스 번호가 당첨 번호와 중복일 경우 예외가 발생한다")
        void isDuplicate() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> BonusNumber.register("1", winningLotto))
                    .withMessage(ExceptionMessage.BONUS_NUMBER_IS_DUPLICATE.getMessage());
        }
    }


    private WinningNumberExtractor mockWinningNumberExtractor(List<Integer> winningNumbers) {
        return (rawWinningNumbers) -> winningNumbers;
    }
}
