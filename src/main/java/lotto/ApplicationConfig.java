package lotto;

import lotto.adapter.console.ConsoleLottoController;
import lotto.adapter.console.ConsolePresenter;

public class ApplicationConfig {
    public ConsoleLottoController createConsoleLottoController() {
        ConsolePresenter consolePresenter = new ConsolePresenter();

        return new ConsoleLottoController(consolePresenter);
    }
}
