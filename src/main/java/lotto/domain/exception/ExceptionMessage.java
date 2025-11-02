package lotto.domain.exception;

public enum ExceptionMessage {
    PURCHASE_AMOUNT_IS_EMPTY("[ERROR] 구입금액은 공백일 수 없습니다."),
    PURCHASE_AMOUNT_IS_NOT_DIGIT("[ERROR] 구입금액을 정수로 변환할 수 없습니다."),
    PURCHASE_AMOUNT_IS_NOT_POSITIVE("[ERROR] 구입금액은 양수만 입력 가능합니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
