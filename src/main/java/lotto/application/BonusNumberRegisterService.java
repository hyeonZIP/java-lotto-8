package lotto.application;

import lotto.application.provided.BonusNumberRegister;
import lotto.domain.BonusNumber;
import lotto.domain.WinningLotto;

public class BonusNumberRegisterService implements BonusNumberRegister {
    @Override
    public BonusNumber registerBonusNumber(String bonusNumber, WinningLotto winningLotto) {
        return BonusNumber.register(bonusNumber, winningLotto);
    }
}
