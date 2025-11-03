package lotto.adapter.console;

import java.util.List;
import java.util.stream.Collectors;
import lotto.adapter.console.dto.LottoDispenserResponse;

public class ConsolePresenter {
    private static final String PURCHASE_AMOUNT_GUIDE = "구입금액을 입력해 주세요.";
    private static final String DISPENSED_LOTTO_COUNT_GUIDE_FORMAT = "\n%s개를 구매했습니다.\n";
    private static final String DISPENSED_LOTTO_NUMBERS_DETAIL_FORMAT = "[%s]\n";
    private static final String COMMA_DELIMITER = ", ";
    private static final String WINNING_NUMBERS_INPUT_GUIDE = "당첨 번호를 입력해 주세요.";

    public void printPurchaseAmountGuide() {
        System.out.println(PURCHASE_AMOUNT_GUIDE);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printLottoDispenserResult(LottoDispenserResponse response) {
        System.out.printf(DISPENSED_LOTTO_COUNT_GUIDE_FORMAT, response.lottos().size());

        response.lottos().forEach(o -> printNumbersDetail(o.numbers()));
    }

    public void printWinningNumbersInputGuide() {
        System.out.println(WINNING_NUMBERS_INPUT_GUIDE);
    }

    private void printNumbersDetail(List<Integer> numbers) {
        String numbersDetail = numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(COMMA_DELIMITER));

        System.out.printf(DISPENSED_LOTTO_NUMBERS_DETAIL_FORMAT, numbersDetail);
    }
}
