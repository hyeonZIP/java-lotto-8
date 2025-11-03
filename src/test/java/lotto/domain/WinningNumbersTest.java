package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.adapter.extractor.Splitter;
import lotto.application.required.WinningNumberExtractor;
import lotto.domain.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumbersTest {
    @Nested
    @DisplayName("성공 케이스")
    class Success {

        @Test
        @DisplayName("올바른 당첨 번호 문자열을 입력하면 예외가 발생하지 않는다")
        void createWinningLotto() {
            String winningNumbers = "1,2,3,4,5,6";
            WinningNumberExtractor extractor = mockWinningNumberExtractor(List.of(1, 2, 3, 4, 5, 6));

            assertThatCode(() -> WinningNumbers.of(winningNumbers, extractor))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    class Fail {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"   ", "\t"})
        @DisplayName("당첨 번호가 공백일 경우 예외가 발생한다")
        void isBlank(String rawWinningNumbers) {
            WinningNumberExtractor extractor = mockWinningNumberExtractor(List.of(1, 2, 3, 4, 5, 6));

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> WinningNumbers.of(rawWinningNumbers, extractor))
                    .withMessage(ExceptionMessage.WINNING_NUMBERS_IS_BLANK.getMessage());
        }

        @Test
        @DisplayName("당첨 번호가 정수로 변환할 수 없는 경우 예외가 발생한다")
        void isNotDigit() {
            String winningNumbers = "1,2,3,사,오,육";
            WinningNumberExtractor extractor = new Splitter();

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> WinningNumbers.of(winningNumbers, extractor))
                    .withMessage(ExceptionMessage.WINNING_NUMBERS_IS_NOT_DIGIT.getMessage());
        }
    }

    private WinningNumberExtractor mockWinningNumberExtractor(List<Integer> winningNumbers) {
        return (rawWinningNumbers) -> winningNumbers;
    }
}
