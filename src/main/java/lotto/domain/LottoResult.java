package lotto.domain;

public class LottoResult {
    private final LottoReward lottoReward;

    private LottoResult(LottoReward lottoReward) {
        this.lottoReward = lottoReward;
    }

    public static LottoResult of(WinningLotto winningLotto, BonusNumber bonusNumber, Lotto purchaseLotto) {
        int matchCount = winningLotto.countMatches(purchaseLotto);
        boolean hasBonusNumber = bonusNumber.contains(purchaseLotto);

        return new LottoResult(LottoReward.of(matchCount, hasBonusNumber));
    }
}
