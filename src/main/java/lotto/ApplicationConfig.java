package lotto;

import lotto.adapter.console.ConsoleInputReader;
import lotto.adapter.console.ConsoleLottoController;
import lotto.adapter.console.ConsolePresenter;

public class ApplicationConfig {
    public ConsoleLottoController createConsoleLottoController() {
        ConsolePresenter consolePresenter = new ConsolePresenter();
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();

        return new ConsoleLottoController(consolePresenter, consoleInputReader);
    }
}
