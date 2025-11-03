package lotto.domain;

import java.util.List;
import lotto.application.required.WinningNumberExtractor;
import lotto.domain.exception.ExceptionMessage;

public class WinningLotto {
    private final Lotto lotto;

    public WinningLotto(List<Integer> winningNumbers) {
        this.lotto = Lotto.createWinningLotto(winningNumbers);
    }

    public boolean isOutOfLottoNumberRange(int number) {
        return lotto.isOutOfLottoNumberRange(number);
    }

    public static WinningLotto of(String winningNumbers, WinningNumberExtractor extractor) {
        validateBlank(winningNumbers);

        return new WinningLotto(extract(winningNumbers, extractor));
    }

    public boolean contains(int number) {
        return lotto.getNumbers().contains(number);
    }

    private static List<Integer> extract(String winningNumbers, WinningNumberExtractor extractor) {
        try {
            return extractor.extract(winningNumbers);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.WINNING_NUMBERS_IS_NOT_DIGIT.getMessage());
        }
    }

    private static void validateBlank(String winningNumbers) {
        if (winningNumbers == null || winningNumbers.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.WINNING_NUMBERS_IS_BLANK.getMessage());
        }
    }
}
