package lotto;

import lotto.adapter.console.ConsoleInputReader;
import lotto.adapter.console.ConsoleLottoController;
import lotto.adapter.console.ConsolePresenter;
import lotto.adapter.random.WootecoRandomNumberGenerator;
import lotto.application.LottoDispenserService;
import lotto.application.LottoOrderService;
import lotto.application.required.RandomNumberGenerator;

public class ApplicationConfig {
    public ConsoleLottoController createConsoleLottoController() {
        ConsolePresenter consolePresenter = new ConsolePresenter();
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();

        LottoOrderService lottoOrderService = new LottoOrderService();
        LottoDispenserService lottoDispenserService = getLottoDispenserService();

        return new ConsoleLottoController(
                consolePresenter,
                consoleInputReader,
                lottoOrderService,
                lottoDispenserService
        );
    }

    private LottoDispenserService getLottoDispenserService() {
        RandomNumberGenerator randomNumberGenerator = new WootecoRandomNumberGenerator();
        return new LottoDispenserService(randomNumberGenerator);
    }
}
