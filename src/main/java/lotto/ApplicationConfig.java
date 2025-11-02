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
        LottoOrderService lottoService = new LottoOrderService();
        RandomNumberGenerator randomNumberGenerator = new WootecoRandomNumberGenerator();
        LottoDispenserService lottoDispenserService = new LottoDispenserService(randomNumberGenerator);

        return new ConsoleLottoController(consolePresenter, consoleInputReader, lottoService, lottoDispenserService);
    }
}
