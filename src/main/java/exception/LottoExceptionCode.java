package exception;

public enum LottoExceptionCode {

    INVALID_LOTTO_NUMBER("올바르지 않은 범위의 로또 숫자 입니다."),
    INVALID_LOTTO_LENGTH("로또 숫자의 갯수가 6개가 아닙니다."),
    INVALID_LOTTO_MATCH_NUMBER("정의되지 않은 로또 일치 숫자입니다.");

    private final String errorMessage;

    LottoExceptionCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
