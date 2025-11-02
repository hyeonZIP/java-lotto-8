package lotto.adapter.console;

import lotto.application.LottoDispenserService;
import lotto.application.LottoOrderService;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;

public class ConsoleLottoController {
    private final ConsolePresenter consolePresenter;
    private final ConsoleInputReader consoleInputReader;
    private final LottoOrderService lottoService;
    private final LottoDispenserService lottoDispenserService;

    public ConsoleLottoController(ConsolePresenter consolePresenter, ConsoleInputReader consoleInputReader,
                                  LottoOrderService lottoService, LottoDispenserService lottoDispenserService) {
        this.consolePresenter = consolePresenter;
        this.consoleInputReader = consoleInputReader;
        this.lottoService = lottoService;
        this.lottoDispenserService = lottoDispenserService;
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        Lottos lottos = getLottos(purchaseAmount);
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

    private Lottos getLottos(PurchaseAmount purchaseAmount) {
        return lottoDispenserService.dispenseLottos(purchaseAmount);
    }
}
