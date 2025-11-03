package lotto.domain;

public class LottoResult {
    private final LottoReward lottoReward;

    private LottoResult(LottoReward lottoReward) {
        this.lottoReward = lottoReward;
    }

    public static LottoResult of(WinningNumbers winningNumbers, BonusNumber bonusNumber, Lotto purchasedLotto) {
        int matchCount = winningNumbers.countMatches(purchasedLotto);
        boolean hasBonusNumber = bonusNumber.contains(purchasedLotto);

        return new LottoResult(LottoReward.of(matchCount, hasBonusNumber));
    }

    public LottoReward getLottoReward() {
        return lottoReward;
    }
}
