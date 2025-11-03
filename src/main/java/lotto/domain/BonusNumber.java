package lotto.domain;

import lotto.domain.exception.ExceptionMessage;

public class BonusNumber {
    private final int value;

    private BonusNumber(int value) {
        this.value = value;
    }

    public static BonusNumber of(String rawBonusNumber, WinningLotto winningLotto) {
        validateBlank(rawBonusNumber);

        int bonusNumber = parse(rawBonusNumber);

        validateRange(bonusNumber, winningLotto);
        validateDuplicate(bonusNumber, winningLotto);

        return new BonusNumber(bonusNumber);
    }

    public boolean contains(Lotto purchaseLotto) {
        return purchaseLotto.hasContains(value);
    }

    private static void validateDuplicate(int bonusNumber, WinningLotto winningLotto) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.BONUS_NUMBER_IS_DUPLICATE.getMessage());
        }
    }

    private static void validateRange(int bonusNumber, WinningLotto winningLotto) {
        if (winningLotto.isOutOfLottoNumberRange(bonusNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.BONUS_NUMBER_IS_OUT_OF_RANGE.getMessage());
        }
    }

    private static int parse(String rawBonusNumber) {
        try {
            return Integer.parseInt(rawBonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.BONUS_NUMBER_IS_NOT_DIGIT.getMessage());
        }
    }

    private static void validateBlank(String bonusNumber) {
        if (bonusNumber == null || bonusNumber.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.BONUS_NUMBER_IS_BLANK.getMessage());
        }
    }
}
