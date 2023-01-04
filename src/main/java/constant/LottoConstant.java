package constant;

public class LottoConstant {
    // 로또 기본 정보 (로또번호 최대값, 최소값, 번호 개수)
    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_COUNT = 6;

    // 로또 상금
    public static final long LOTTO_1ST_PRIZE = 2_000_000_000L;
    public static final long LOTTO_2ND_PRIZE = 30_000_000L;
    public static final long LOTTO_3RD_PRIZE = 1_500_000L;
    public static final long LOTTO_4TH_PRIZE = 50_000L;
    public static final long LOTTO_5TH_PRIZE = 5000L;

    public static final int LOTTO_TICKET_PRICE = 1000;

    // 로또 번호 집합 input시 delimiter
    public static final String LOTTO_NUMBER_DELIMITER = ", ";
}
