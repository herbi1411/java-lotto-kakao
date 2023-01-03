package exception;

public class LottoException extends RuntimeException {

    public LottoException(LottoExceptionCode lottoExceptionCode) {
        super(lottoExceptionCode.getErrorMessage());
    }
}
