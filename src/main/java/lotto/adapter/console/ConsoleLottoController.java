package lotto.adapter.console;

public class ConsoleLottoController {
    private final ConsolePresenter consolePresenter;

    public ConsoleLottoController(ConsolePresenter consolePresenter) {
        this.consolePresenter = consolePresenter;
    }

    public void run() {
        consolePresenter.printPurchaseAmountGuide();
    }
}
