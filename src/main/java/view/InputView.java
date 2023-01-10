package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Long inputMoney() {
        System.out.println("구매금액을 입력해 주세요.");
        return Long.parseLong(scanner.nextLine());
    }

    public String getLottoString() {
        System.out.println("지난 주 당첨 번호를 입Ï력해 주세요.");
        return scanner.nextLine();
    }

    public int getBonusBallNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public Long inputManualLottoNumber() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주새요.");
        return Long.parseLong(scanner.nextLine());
    }

    public List<String> getManualLottoGroupString(Long manualLottoInputNumber) {
        if (manualLottoInputNumber > 0) {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        }

        return LongStream.range(0, manualLottoInputNumber)
                .boxed()
                .map(i -> scanner.nextLine())
                .collect(Collectors.toList());
    }
}
