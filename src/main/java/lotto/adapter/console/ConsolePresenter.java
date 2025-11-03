package lotto.adapter.console;

import lotto.adapter.console.dto.LottoDispenserResponse;

public class ConsolePresenter {
    private static final String PURCHASE_AMOUNT_GUIDE = "구입금액을 입력해 주세요.";
    private static final String DISPENSED_LOTTO_COUNT_GUIDE_FORMAT = "\n%s개를 구매했습니다.\n";

    public void printPurchaseAmountGuide() {
        System.out.println(PURCHASE_AMOUNT_GUIDE);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printLottoDispenserResult(LottoDispenserResponse response) {
        System.out.printf(DISPENSED_LOTTO_COUNT_GUIDE_FORMAT, response.lottos().size());
    }
}
