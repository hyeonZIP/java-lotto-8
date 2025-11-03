package lotto.application;

import lotto.application.provided.WinningLottoRegister;
import lotto.application.required.WinningNumberExtractor;
import lotto.domain.WinningLotto;

public class WinningLottoRegisterService implements WinningLottoRegister {
    private final WinningNumberExtractor extractor;

    public WinningLottoRegisterService(WinningNumberExtractor extractor) {
        this.extractor = extractor;
    }

    @Override
    public WinningLotto registerWinningLotto(String rawWinningNumbers) {
        return WinningLotto.of(rawWinningNumbers, extractor);
    }
}
