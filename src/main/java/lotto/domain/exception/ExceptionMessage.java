package lotto.domain.exception;

public enum ExceptionMessage {
    PURCHASE_AMOUNT_IS_EMPTY("[ERROR] 구입금액은 공백일 수 없습니다."),
    PURCHASE_AMOUNT_IS_NOT_DIGIT("[ERROR] 구입금액을 정수로 변환할 수 없습니다."),
    PURCHASE_AMOUNT_IS_NOT_POSITIVE("[ERROR] 구입금액은 양수만 입력 가능합니다."),
    PURCHASE_AMOUNT_IS_INDIVISIBLE("[ERROR] 구입금액은 로또 1장 가격(1,000)으로 나누어 떨어져야 합니다."),
    PURCHASE_AMOUNT_IS_OVER_MAXIMUM("[ERROR] 구입금액은 1억원을 넘을 수 없습니다."),

    LOTTO_INVALID_SIZE("[ERROR] 로또 번호의 개수가 6개가 아닌 경우가 있습니다."),
    LOTTO_IS_DUPLICATE("[ERROR] 로또 번호가 중복되는 경우가 있습니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
