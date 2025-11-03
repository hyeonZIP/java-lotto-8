package lotto.adapter.console.dto;

import lotto.domain.LottoReward;

public record RewardDetail(int matchCount, boolean hasBonusNumber, int reward, long count) {
    public static RewardDetail of(LottoReward lottoReward, long count) {
        return new RewardDetail(
                lottoReward.getMatchCount(),
                lottoReward.getHasBonusNumber(),
                lottoReward.getReward(),
                count);
    }
}
