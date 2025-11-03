package lotto.adapter.console.dto;

import java.util.List;
import lotto.domain.Lotto;

public record NumbersDetail(List<Integer> numbers) {
    public static NumbersDetail of(Lotto lotto) {
        return new NumbersDetail(lotto.getNumbers());
    }
}
