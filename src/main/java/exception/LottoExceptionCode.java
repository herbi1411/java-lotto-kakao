package exception;

public enum LottoExceptionCode {
    INVALID_LOTTO_NUMBER("로또 범위에 맞지 않는 숫자입니다.");
    private final String message;

    LottoExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
