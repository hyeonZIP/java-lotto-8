package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto lotto;

    public WinningLotto(List<Integer> winningNumbers) {
        this.lotto = Lotto.createWinningLotto(winningNumbers);
    }
}
