package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<LottoReward, Long> tallyReward() {
        return results.stream()
                .collect(Collectors.groupingBy(LottoResult::getLottoReward, Collectors.counting()));
    }
}
