package lotto.adapter.console;

import lotto.adapter.console.dto.LottoDispenserResponse;
import lotto.adapter.console.dto.LottoResultResponse;
import lotto.application.BonusNumberRegisterService;
import lotto.application.LottoDispenserService;
import lotto.application.LottoOrderService;
import lotto.application.WinningLottoRegisterService;
import lotto.domain.BonusNumber;
import lotto.domain.LottoResults;
import lotto.domain.Lottos;
import lotto.domain.PurchasedAmount;
import lotto.domain.WinningLotto;

public class ConsoleLottoController {
    private final ConsolePresenter consolePresenter;
    private final ConsoleInputReader consoleInputReader;
    private final LottoOrderService lottoOrderService;
    private final LottoDispenserService lottoDispenserService;
    private final WinningLottoRegisterService winningLottoRegisterService;
    private final BonusNumberRegisterService bonusNumberRegisterService;

    public ConsoleLottoController(ConsolePresenter consolePresenter, ConsoleInputReader consoleInputReader,
                                  LottoOrderService lottoOrderService, LottoDispenserService lottoDispenserService,
                                  WinningLottoRegisterService winningLottoRegisterService,
                                  BonusNumberRegisterService bonusNumberRegisterService) {
        this.consolePresenter = consolePresenter;
        this.consoleInputReader = consoleInputReader;
        this.lottoOrderService = lottoOrderService;
        this.lottoDispenserService = lottoDispenserService;
        this.winningLottoRegisterService = winningLottoRegisterService;
        this.bonusNumberRegisterService = bonusNumberRegisterService;
    }

    public void run() {
        PurchasedAmount purchasedAmount = getPurchaseAmount();

        Lottos lottos = getLottos(purchasedAmount);
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
                return bonusNumberRegisterService.registerBonusNumber(rawBonusNumber, winningLotto);
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
                return winningLottoRegisterService.registerWinningLotto(rawWinningNumbers);
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
                return lottoOrderService.orderPurchaseAmount(rawPurchaseAmount);
            } catch (IllegalArgumentException e) {
                consolePresenter.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lottos getLottos(PurchasedAmount purchasedAmount) {
        return lottoDispenserService.dispenseLottos(purchasedAmount);
    }
}
