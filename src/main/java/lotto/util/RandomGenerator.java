package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import static lotto.constant.LottoConstant.MIN_LOTTO_NUMBER;

public final class RandomGenerator {
    private RandomGenerator() {
    }

    public static int returnRandomNumber() {
        return Randoms.pickNumberInRange(MIN_LOTTO_NUMBER, MIN_LOTTO_NUMBER);
    }
}
