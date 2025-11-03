package lotto.adapter.console;

import lotto.adapter.console.dto.LottoDispenserResponse;
import lotto.adapter.console.dto.LottoResultResponse;
import lotto.application.required.RandomNumberGenerator;
import lotto.application.required.WinningNumberExtractor;
import lotto.domain.BonusNumber;
import lotto.domain.LottoResults;
import lotto.domain.Lottos;
import lotto.domain.PurchasedAmount;
import lotto.domain.WinningNumbers;

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

        WinningNumbers winningNumbers = getWinningLotto();

        BonusNumber bonusNumber = getBonusNumber(winningNumbers);

        LottoResults results = LottoResults.of(winningNumbers, bonusNumber, lottos);

        consolePresenter.printLottoResult(LottoResultResponse.of(results, purchasedAmount));
    }

    private BonusNumber getBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                consolePresenter.printBonusNumberInputGuide();
                String rawBonusNumber = consoleInputReader.getConsoleInput();
                return BonusNumber.of(rawBonusNumber, winningNumbers);
            } catch (IllegalArgumentException e) {
                consolePresenter.printErrorMessage(e.getMessage());
            }
        }
    }

    private WinningNumbers getWinningLotto() {
        while (true) {
            try {
                consolePresenter.printWinningNumbersInputGuide();
                String rawWinningNumbers = consoleInputReader.getConsoleInput();
                return WinningNumbers.of(rawWinningNumbers, winningNumberExtractor);
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
