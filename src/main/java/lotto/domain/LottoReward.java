package lotto.domain;

import java.util.Arrays;

public enum LottoReward {
    NONE(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    ;

    private int matchCount;
    private boolean hasBonusNumber;
    private int reward;

    LottoReward(int matchCount, boolean hasBonusNumber, int reward) {
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
        this.reward = reward;
    }

    public static LottoReward of(int matchCount, boolean hasBonusNumber) {
        return Arrays.stream(values())
                .filter(lottoReward ->
                        lottoReward.matchCount == matchCount
                                && lottoReward.hasBonusNumber == hasBonusNumber)
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean getHasBonusNumber() {
        return hasBonusNumber;
    }

    public int getReward() {
        return reward;
    }
}
