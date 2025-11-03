package lotto.application.required;

import java.util.List;

public interface WinningNumberExtractor {
    List<Integer> extract(String winningNumbers);
}
