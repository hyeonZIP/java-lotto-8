package lotto.domain;

import java.util.List;
import lotto.application.required.WinningNumberExtractor;
import lotto.domain.exception.ExceptionMessage;

public class WinningLotto {
    private final Lotto lotto;

    private WinningLotto(List<Integer> winningNumbers) {
        this.lotto = Lotto.createWinningLotto(winningNumbers);
    }

    public static WinningLotto of(String winningNumbers, WinningNumberExtractor extractor) {
        validateBlank(winningNumbers);

        return new WinningLotto(extract(winningNumbers, extractor));
    }

    public boolean contains(int number) {
        return lotto.hasContains(number);
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

    public int countMatches(Lotto purchaseLotto) {
        List<Integer> purchaseNumbers = purchaseLotto.getNumbers();
        List<Integer> winningNumbers = lotto.getNumbers();

        return (int) purchaseNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean isOutOfLottoNumberRange(int number) {
        return lotto.isOutOfLottoNumberRange(number);
    }
}
