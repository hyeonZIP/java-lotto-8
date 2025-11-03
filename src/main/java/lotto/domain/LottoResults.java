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

    public Double calculateRevenueRate(PurchaseAmount purchaseAmount) {
        int totalReward = getTotalReward();

        return (double) (totalReward / purchaseAmount.getAmount() * 100);
    }

    private int getTotalReward() {
        return results.stream()
                .mapToInt(lottoResult -> lottoResult.getLottoReward().getReward())
                .sum();
    }
}
