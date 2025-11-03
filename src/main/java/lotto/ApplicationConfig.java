package lotto;

import lotto.adapter.console.ConsoleInputReader;
import lotto.adapter.console.ConsoleLottoController;
import lotto.adapter.console.ConsolePresenter;
import lotto.adapter.extractor.Splitter;
import lotto.adapter.random.WootecoRandomNumberGenerator;
import lotto.application.required.RandomNumberGenerator;
import lotto.application.required.WinningNumberExtractor;

public class ApplicationConfig {
    public ConsoleLottoController createConsoleLottoController() {
        ConsolePresenter consolePresenter = new ConsolePresenter();
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();

        RandomNumberGenerator randomNumberGenerator = new WootecoRandomNumberGenerator();
        WinningNumberExtractor winningNumberExtractor = new Splitter();

        return new ConsoleLottoController(
                consolePresenter,
                consoleInputReader,
                randomNumberGenerator,
                winningNumberExtractor);
    }
}
