package lotto.adapter.console;

public class ConsoleLottoController {
    private final ConsolePresenter consolePresenter;
    private final ConsoleInputReader consoleInputReader;

    public ConsoleLottoController(ConsolePresenter consolePresenter, ConsoleInputReader consoleInputReader) {
        this.consolePresenter = consolePresenter;
        this.consoleInputReader = consoleInputReader;
    }

    public void run() {
        consolePresenter.printPurchaseAmountGuide();
        String rawPurchaseAmount = consoleInputReader.getConsoleInput();
    }
}
