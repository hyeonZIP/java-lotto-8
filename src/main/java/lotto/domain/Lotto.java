package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.exception.ExceptionMessage;

public class Lotto {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLotto(numbers);
        this.numbers = numbers;
    }

    private void validateLotto(List<Integer> numbers) {
        validateLottoNumbersCount(numbers);
        validateLottoNumbersDuplicate(numbers);
        validateLottoNumberRange(numbers);
    }

    private void validateLottoNumberRange(List<Integer> numbers) {
        if (isOutOfLottoNumberRange(numbers)) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_INVALID_RANGE.getMessage());
        }
    }

    private boolean isOutOfLottoNumberRange(List<Integer> numbers) {
        return numbers.stream().anyMatch(
                number -> number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER);
    }

    private void validateLottoNumbersCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_INVALID_SIZE.getMessage());
        }
    }

    private void validateLottoNumbersDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_IS_DUPLICATE.getMessage());
        }
    }
}
