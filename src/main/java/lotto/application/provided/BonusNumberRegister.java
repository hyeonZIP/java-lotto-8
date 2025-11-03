package lotto.application.provided;

import lotto.domain.BonusNumber;
import lotto.domain.WinningLotto;

public interface BonusNumberRegister {
    BonusNumber registerBonusNumber(String bonusNumber, WinningLotto winningLotto);
}
