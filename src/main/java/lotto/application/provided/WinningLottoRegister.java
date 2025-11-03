package lotto.application.provided;

import lotto.domain.WinningLotto;

public interface WinningLottoRegister {
    WinningLotto registerWinningLotto(String rawWinningNumbers);
}
