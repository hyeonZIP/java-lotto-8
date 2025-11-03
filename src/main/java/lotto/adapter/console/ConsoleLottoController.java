package lotto.adapter.console;

import lotto.adapter.console.dto.LottoDispenserResponse;
import lotto.adapter.console.dto.LottoResultResponse;
import lotto.application.required.RandomNumberGenerator;
import lotto.application.required.WinningNumberExtractor;
import lotto.domain.BonusNumber;
import lotto.domain.LottoResults;
import lotto.domain.Lottos;
import lotto.domain.PurchasedAmount;
import lotto.domain.WinningLotto;

public class ConsoleLottoController {
    private final ConsolePresenter consolePresenter;
    private final ConsoleInputReader consoleInputReader;
    private final RandomNumberGenerator randomNumberGenerator;
    private final WinningNumberExtractor winningNumberExtractor;

    public ConsoleLottoController(ConsolePresenter consolePresenter, ConsoleInputReader consoleInputReader,
                                  RandomNumberGenerator randomNumberGenerator,
                                  WinningNumberExtractor winningNumberExtractor) {
        this.consolePresenter = consolePresenter;
        this.consoleInputReader = consoleInputReader;
        this.randomNumberGenerator = randomNumberGenerator;
        this.winningNumberExtractor = winningNumberExtractor;
    }

    public void run() {
        PurchasedAmount purchasedAmount = getPurchaseAmount();

        Lottos lottos = Lottos.of(purchasedAmount, randomNumberGenerator);
        consolePresenter.printLottoDispenserResult(LottoDispenserResponse.of(lottos));

        WinningLotto winningLotto = getWinningLotto();

        BonusNumber bonusNumber = getBonusNumber(winningLotto);

        LottoResults results = LottoResults.of(winningLotto, bonusNumber, lottos);

        consolePresenter.printLottoResult(LottoResultResponse.of(results, purchasedAmount));
    }

    private BonusNumber getBonusNumber(WinningLotto winningLotto) {
        while (true) {
            try {
                consolePresenter.printBonusNumberInputGuide();
                String rawBonusNumber = consoleInputReader.getConsoleInput();
                return BonusNumber.register(rawBonusNumber, winningLotto);
            } catch (IllegalArgumentException e) {
                consolePresenter.printErrorMessage(e.getMessage());
            }
        }
    }

    private WinningLotto getWinningLotto() {
        while (true) {
            try {
                consolePresenter.printWinningNumbersInputGuide();
                String rawWinningNumbers = consoleInputReader.getConsoleInput();
                return WinningLotto.of(rawWinningNumbers, winningNumberExtractor);
            } catch (IllegalArgumentException e) {
                consolePresenter.printErrorMessage(e.getMessage());
            }
        }
    }

    private PurchasedAmount getPurchaseAmount() {
        while (true) {
            try {
                consolePresenter.printPurchaseAmountGuide();
                String rawPurchaseAmount = consoleInputReader.getConsoleInput();
                return PurchasedAmount.of(rawPurchaseAmount);
            } catch (IllegalArgumentException e) {
                consolePresenter.printErrorMessage(e.getMessage());
            }
        }
    }
}
