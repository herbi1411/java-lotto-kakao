package exception;

public enum LottoExceptionCode {

    INVALID_LOTTO_NUMBER("올바르지 않은 범위의 로또 숫자 입니다."),
    INVALID_LOTTO_LENGTH("로또 숫자의 갯수가 6개가 아닙니다."),
    INVALID_LOTTO_MATCH_NUMBER("정의되지 않은 로또 일치 숫자입니다."),
    ILLEGAL_ARGUMENT_LOTTO_STRING("입렫받은 로또 번호를 처리할 수 없습니다."),
    WINNING_LOTTO_SET_DUPLICATE("보너스 볼과 당첨 번호가 중복됩니다."),
    NEGATIVE_MONEY("금액은 양수로 입력돼야 합니다."),
    INVALID_MANUAL_LOTTO_COUNT("입력한 구입 금액으로 해당 로또 수만큼 구매할 수 없습니다.");

    private final String errorMessage;

    LottoExceptionCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
