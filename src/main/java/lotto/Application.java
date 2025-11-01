package lotto;

import lotto.adapter.console.ConsoleLottoController;

public class Application {
    public static void main(String[] args) {
        ApplicationConfig config = new ApplicationConfig();
        ConsoleLottoController consoleLottoController = config.createConsoleLottoController();

        consoleLottoController.run();
    }
}
