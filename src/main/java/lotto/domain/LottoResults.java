package lotto.domain;

import java.util.List;

public class LottoResults {
    private final List<LottoResult> results;

    private LottoResults(List<LottoResult> results) {
        this.results = List.copyOf(results);
    }

    public static LottoResults of(WinningLotto winningLotto, BonusNumber bonusNumber, Lottos purchaseLottos) {
        List<LottoResult> results = purchaseLottos.getLottos().stream()
                .map(purchaseLotto -> LottoResult.of(winningLotto, bonusNumber, purchaseLotto))
                .toList();

        return new LottoResults(results);
    }
}
