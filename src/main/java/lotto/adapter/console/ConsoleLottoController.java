package lotto.adapter.console;

import lotto.adapter.console.dto.LottoDispenserResponse;
import lotto.application.LottoDispenserService;
import lotto.application.LottoOrderService;
import lotto.application.WinningLottoRegisterService;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;

public class ConsoleLottoController {
    private final ConsolePresenter consolePresenter;
    private final ConsoleInputReader consoleInputReader;
    private final LottoOrderService lottoOrderService;
    private final LottoDispenserService lottoDispenserService;
    private final WinningLottoRegisterService winningLottoRegisterService;

    public ConsoleLottoController(ConsolePresenter consolePresenter, ConsoleInputReader consoleInputReader,
                                  LottoOrderService lottoOrderService, LottoDispenserService lottoDispenserService,
                                  WinningLottoRegisterService winningLottoRegisterService) {
        this.consolePresenter = consolePresenter;
        this.consoleInputReader = consoleInputReader;
        this.lottoOrderService = lottoOrderService;
        this.lottoDispenserService = lottoDispenserService;
        this.winningLottoRegisterService = winningLottoRegisterService;
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();

        Lottos lottos = getLottos(purchaseAmount);
        consolePresenter.printLottoDispenserResult(LottoDispenserResponse.of(lottos));

        WinningLotto winningLotto = getWinningLotto();

        consolePresenter.printBonusNumberInputGuide();
        String rawBonusNumber = consoleInputReader.getConsoleInput();
    }

    private WinningLotto getWinningLotto() {
        while (true) {
            try {
                consolePresenter.printWinningNumbersInputGuide();
                String rawWinningNumbers = consoleInputReader.getConsoleInput();
                return winningLottoRegisterService.registerWinningLotto(rawWinningNumbers);
            } catch (IllegalArgumentException e) {
                consolePresenter.printErrorMessage(e.getMessage());
            }
        }
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
