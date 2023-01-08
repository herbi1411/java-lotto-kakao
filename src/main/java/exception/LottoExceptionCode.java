package exception;

public enum LottoExceptionCode {
    INVALID_LOTTO_NUMBER("로또 범위에 맞지 않는 숫자입니다."),
    INVALID_LOTTO_LENGTH("잘못된 로또 길이입니다."),
    DUPLICATE_LOTTO_NUMBER("로또 숫자가 중복됐습니다."),
    INVALID_LOTTO_STRING("로또 문자열을 숫자로 변환할 수 없습니다.");
    private final String message;

    LottoExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
