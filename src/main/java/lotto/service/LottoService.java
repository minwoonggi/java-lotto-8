package lotto.service;

import lotto.domain.Lotto;
import lotto.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import static lotto.constant.LottoConstant.LOTTO_SIZE;

public class LottoService {
    public Lotto getNewLotto() {
        List<Integer> LottoNumbers = creatNonDuplicationLottoNumbers();
        Lotto lotto = new Lotto(LottoNumbers);

        return lotto;
    }

    private List<Integer> creatNonDuplicationLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();

        while (lottoNumbers.size() < LOTTO_SIZE) {
            int newRandomNumber = RandomGenerator.returnRandomNumber();
            if (!lottoNumbers.contains(newRandomNumber)) {
                lottoNumbers.add(newRandomNumber);
            }
        }

        return lottoNumbers;
    }
}


