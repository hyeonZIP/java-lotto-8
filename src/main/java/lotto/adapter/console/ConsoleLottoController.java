package lotto.adapter.console;

import lotto.adapter.console.dto.LottoDispenserResponse;
import lotto.application.LottoDispenserService;
import lotto.application.LottoOrderService;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;

public class ConsoleLottoController {
    private final ConsolePresenter consolePresenter;
    private final ConsoleInputReader consoleInputReader;
    private final LottoOrderService lottoOrderService;
    private final LottoDispenserService lottoDispenserService;

    public ConsoleLottoController(ConsolePresenter consolePresenter, ConsoleInputReader consoleInputReader,
                                  LottoOrderService lottoOrderService, LottoDispenserService lottoDispenserService) {
        this.consolePresenter = consolePresenter;
        this.consoleInputReader = consoleInputReader;
        this.lottoOrderService = lottoOrderService;
        this.lottoDispenserService = lottoDispenserService;
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        Lottos lottos = getLottos(purchaseAmount);
        consolePresenter.printLottoDispenserResult(LottoDispenserResponse.of(lottos));

        consolePresenter.printWinningNumbersInputGuide();
    }

    private PurchaseAmount getPurchaseAmount() {
        while (true) {
            try {
                consolePresenter.printPurchaseAmountGuide();
                String rawPurchaseAmount = consoleInputReader.getConsoleInput();
                return lottoOrderService.orderPurchaseAmount(rawPurchaseAmount);
            } catch (IllegalArgumentException e) {
                consolePresenter.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lottos getLottos(PurchaseAmount purchaseAmount) {
        return lottoDispenserService.dispenseLottos(purchaseAmount);
    }
}
