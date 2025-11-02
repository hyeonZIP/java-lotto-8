package lotto.adapter.console;

import lotto.application.LottoOrderService;
import lotto.domain.PurchaseAmount;

public class ConsoleLottoController {
    private final ConsolePresenter consolePresenter;
    private final ConsoleInputReader consoleInputReader;
    private final LottoOrderService lottoService;

    public ConsoleLottoController(ConsolePresenter consolePresenter, ConsoleInputReader consoleInputReader,
                                  LottoOrderService lottoService) {
        this.consolePresenter = consolePresenter;
        this.consoleInputReader = consoleInputReader;
        this.lottoService = lottoService;
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
    }

    private PurchaseAmount getPurchaseAmount() {
        while (true) {
            try {
                consolePresenter.printPurchaseAmountGuide();
                String rawPurchaseAmount = consoleInputReader.getConsoleInput();
                return lottoService.orderPurchaseAmount(rawPurchaseAmount);
            } catch (IllegalArgumentException e) {
                consolePresenter.printErrorMessage(e.getMessage());
            }
        }
    }
}
