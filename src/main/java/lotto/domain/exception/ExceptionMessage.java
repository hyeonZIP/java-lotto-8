package lotto.domain.exception;

public enum ExceptionMessage {
    PURCHASE_AMOUNT_IS_EMPTY("[ERROR] 구입금액은 공백일 수 없습니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
