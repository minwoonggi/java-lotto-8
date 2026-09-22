package lotto.view;

public class ValidateInput {
    private static final int THOUSAND = 1000;

    public static void validateEmptyInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 비어있는 입력값입니다.");
        }
    }

    public static void validatePositiveNumberInput(int input) {
        if (input < 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액이 양수가 아닙니다.");
        }
    }

    public static void validateMultipleOfThousandsInput(int input) {
        if (input % THOUSAND != 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액이 1000 단위가 아닙니다.");
        }
    }
}
