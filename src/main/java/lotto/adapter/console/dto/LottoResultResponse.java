package lotto.adapter.console.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoResults;
import lotto.domain.LottoReward;
import lotto.domain.PurchasedAmount;

public record LottoResultResponse(List<RewardDetail> details, double revenueRate) {
    public static LottoResultResponse of(LottoResults lottoResults, PurchasedAmount purchasedAmount) {
        Map<LottoReward, Long> tallyRewardResult = lottoResults.tallyReward();

        List<RewardDetail> details = Arrays.stream(LottoReward.values())
                .filter(reward -> reward != LottoReward.NONE)
                .map(reward -> new RewardDetail(
                        reward.getMatchCount(),
                        reward.getHasBonusNumber(),
                        reward.getReward(),
                        tallyRewardResult.getOrDefault(reward, 0L)
                ))
                .toList();

        double revenueRate = lottoResults.calculateRevenueRate(purchasedAmount);

        return new LottoResultResponse(details, revenueRate);
    }
}
