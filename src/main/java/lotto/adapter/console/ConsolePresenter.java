package lotto.adapter.console;

public class ConsolePresenter {
    private static final String PURCHASE_AMOUNT_GUIDE = "구입금액을 입력해 주세요.";

    public void printPurchaseAmountGuide() {
        System.out.println(PURCHASE_AMOUNT_GUIDE);
    }

    public void printErrorMessage(String message){
        System.out.println(message);
    }
}
