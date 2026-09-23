package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.constant.LottoConstant.MAX_LOTTO_NUMBER;
import static lotto.constant.LottoConstant.MIN_LOTTO_NUMBER;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateLottoNumbersDuplication(numbers);
        validateLottoNumbersRange(numbers);
        this.numbers = new ArrayList<>(numbers);
        arrangeNumbers();
    }

    private void arrangeNumbers() {
        Collections.sort(numbers);
    }

    public List<Integer> getUnmodifiableNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateLottoNumbersDuplication(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되면 안됩니다.");
        }
    }

    private void validateLottoNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1 ~ 45 사이 숫자이어야 합니다.");
            }
        }
    }
}
