package lotto;

import lotto.adapter.console.ConsoleInputReader;
import lotto.adapter.console.ConsoleLottoController;
import lotto.adapter.console.ConsolePresenter;
import lotto.application.LottoOrderService;

public class ApplicationConfig {
    public ConsoleLottoController createConsoleLottoController() {
        ConsolePresenter consolePresenter = new ConsolePresenter();
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();
        LottoOrderService lottoService = new LottoOrderService();

        return new ConsoleLottoController(consolePresenter, consoleInputReader, lottoService);
    }
}
