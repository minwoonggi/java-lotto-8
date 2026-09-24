package lotto.view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.constant.LottoConstant.MAX_LOTTO_NUMBER;
import static lotto.constant.LottoConstant.MIN_LOTTO_NUMBER;

public class ValidateInput {
    private static final int THOUSAND = 1000;
    private static final int WINNING_NUMBERS_SIZE = 6;

    public static void validateEmptyInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 비어있는 입력값입니다.");
        }
    }

    public static void validatePositiveNumberInput(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액이 양수가 아닙니다.");
        }
    }

    public static void validateMultipleOfThousandsInput(int input) {
        if (input % THOUSAND != 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액이 1000 단위가 아닙니다.");
        }
    }

    public static void validateWinningNumbersRange(int winningNumber) {
        if (winningNumber > MAX_LOTTO_NUMBER || winningNumber < MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1 ~ 45 사이의 숫자 입니다.");
        }
    }

    public static void validateWinningNumbersDuplication(List<Integer> winningNumbers) {
        Set<Integer> numberSet = new HashSet<>(winningNumbers);
        if (numberSet.size() != winningNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복되면 안됩니다.");
        }
    }

    public static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber > MAX_LOTTO_NUMBER || bonusNumber < MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1 ~ 45 사이의 숫자 입니다.");
        }
    }

    public static void validateBonusNumberDuplicationWithWinningNumber(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호와 당첨번호 사이에 중복이 있습니다.");
        }
    }

    public static void validateWinningNumbersSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != WINNING_NUMBERS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6자여야 합니다.");
        }
    }
}
