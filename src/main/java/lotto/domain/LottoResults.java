package lotto.domain;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResults {
    private static final int PERCENT = 100;
    private final List<LottoResult> results;

    private LottoResults(List<LottoResult> results) {
        this.results = List.copyOf(results);
    }

    public static LottoResults of(WinningNumbers winningNumbers, BonusNumber bonusNumber, Lottos purchaseLottos) {
        List<LottoResult> results = purchaseLottos.getLottos().stream()
                .map(purchaseLotto -> LottoResult.of(winningNumbers, bonusNumber, purchaseLotto))
                .toList();

        return new LottoResults(results);
    }

    public Map<LottoReward, Long> tallyReward() {
        return results.stream()
                .collect(Collectors.groupingBy(LottoResult::getLottoReward, Collectors.counting()));
    }

    public double calculateRevenueRate(PurchasedAmount purchasedAmount) {
        BigInteger totalReward = getTotalReward();

        return (double) totalReward.longValue() / purchasedAmount.getAmount() * PERCENT;
    }

    private BigInteger getTotalReward() {
        BigInteger totalReward = BigInteger.ZERO;

        for (LottoResult result : results) {
            totalReward = totalReward.add(BigInteger.valueOf(result.getLottoReward().getReward()));
        }

        return totalReward;
    }
}
