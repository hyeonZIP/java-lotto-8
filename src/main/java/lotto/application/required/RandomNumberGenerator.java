package lotto.application.required;

import java.util.List;

public interface RandomNumberGenerator {
    List<Integer> generateRandomNumbers(int start, int end, int size);
}
