package lotto.adapter.extractor;

import java.util.Arrays;
import java.util.List;
import lotto.application.required.WinningNumberExtractor;

public class Splitter implements WinningNumberExtractor {
    private static final String DELIMITER = ",";

    @Override
    public List<Integer> extract(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(DELIMITER)).map(Integer::parseInt).toList();
    }
}
