package lotto.domain;

public class LottoResult {
    private final LottoReward lottoReward;

    private LottoResult(LottoReward lottoReward) {
        this.lottoReward = lottoReward;
    }

    public static LottoResult of(WinningNumbers winningNumbers, BonusNumber bonusNumber, Lotto purchaseLotto) {
        int matchCount = winningNumbers.countMatches(purchaseLotto);
        boolean hasBonusNumber = bonusNumber.contains(purchaseLotto);

        return new LottoResult(LottoReward.of(matchCount, hasBonusNumber));
    }

    public LottoReward getLottoReward() {
        return lottoReward;
    }
}
