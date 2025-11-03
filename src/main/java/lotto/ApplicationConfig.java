package lotto;

import lotto.adapter.console.ConsoleInputReader;
import lotto.adapter.console.ConsoleLottoController;
import lotto.adapter.console.ConsolePresenter;
import lotto.adapter.extractor.Splitter;
import lotto.adapter.random.WootecoRandomNumberGenerator;
import lotto.application.BonusNumberRegisterService;
import lotto.application.LottoDispenserService;
import lotto.application.LottoOrderService;
import lotto.application.WinningLottoRegisterService;
import lotto.application.required.RandomNumberGenerator;
import lotto.application.required.WinningNumberExtractor;

public class ApplicationConfig {
    public ConsoleLottoController createConsoleLottoController() {
        ConsolePresenter consolePresenter = new ConsolePresenter();
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();

        LottoOrderService lottoOrderService = new LottoOrderService();
        LottoDispenserService lottoDispenserService = getLottoDispenserService();
        WinningLottoRegisterService winningLottoRegisterService = getWinningLottoRegisterService();
        BonusNumberRegisterService bonusNumberRegisterService = new BonusNumberRegisterService();

        return new ConsoleLottoController(consolePresenter, consoleInputReader, lottoOrderService,
                lottoDispenserService, winningLottoRegisterService, bonusNumberRegisterService);
    }

    private WinningLottoRegisterService getWinningLottoRegisterService() {
        WinningNumberExtractor extractor = new Splitter();
        return new WinningLottoRegisterService(extractor);
    }

    private LottoDispenserService getLottoDispenserService() {
        RandomNumberGenerator randomNumberGenerator = new WootecoRandomNumberGenerator();
        return new LottoDispenserService(randomNumberGenerator);
    }
}
