package lotto.adapter.console.dto;

import java.util.List;
import lotto.domain.Lottos;

public record LottoDispenserResponse(List<NumbersDetail> lottos) {
    public static LottoDispenserResponse of(Lottos lottos) {
        List<NumbersDetail> numbersDetails = lottos.getLottos().stream()
                .map(NumbersDetail::of)
                .toList();

        return new LottoDispenserResponse(numbersDetails);
    }
}
