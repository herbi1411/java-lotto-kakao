package exception;

public enum LottoExceptionCode {

    INVALID_LOTTO_NUMBER("올바르지 않은 범위의 로또 숫자 입니다."),
    INVALID_LOTTO_LENGTH("로또 숫자의 갯수가 6개가 아닙니다."),
    WINNING_LOTTO_SET_DUPLICATE("보너스 볼과 당첨 번호가 중복됩니다."),
    NEGATIVE_MONEY("금액은 양수로 입력돼야 합니다."),
    INVALID_MANUAL_LOTTO_COUNT("입력한 구입 금액으로 해당 로또 수만큼 구매할 수 없습니다."),
    NEGATIVE_MANUAL_LOTTO_COUNT("수동 로또 구입 갯수는 0개 이상이어야 합니다.");

    private final String errorMessage;

    LottoExceptionCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
