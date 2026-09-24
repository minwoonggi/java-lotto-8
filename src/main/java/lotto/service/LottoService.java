package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.util.RandomGenerator;
import java.util.List;
import java.util.Optional;

public class LottoService {
    public Lotto getNewLotto() {
        List<Integer> LottoNumbers = creatNonDuplicationLottoNumbers();
        Lotto lotto = new Lotto(LottoNumbers);

        return lotto;
    }

    private List<Integer> creatNonDuplicationLottoNumbers() {
        List<Integer> lottoNumbers = RandomGenerator.returnRandomNumbers();

        return lottoNumbers;
    }

    public LottoResult getLottoResult(List<Lotto> lottos,
                                      List<Integer> winningNumbers,
                                      int bonusNumber) {
        LottoResult lottoResult = LottoResult.createLottoResult();

        for (Lotto lotto : lottos) {
            int matchCount = lotto.compareWithWinningNumbers(winningNumbers);
            boolean matchWithBonusNumber = lotto.isAnyMatchWithBonusNumber(bonusNumber);
            Optional<LottoRank> lottoRank = LottoRank.getLottoRank(matchCount, matchWithBonusNumber);
            lottoRank.ifPresent(lottoResult::plusRottoRankCount);
        }

        return lottoResult;
    }

    public double getPercentOfReturn(LottoResult lottoResult,
                                     int spentAmount) {
        long lottoResultTotal = lottoResult.getLottoResultTotal();
        double percentOfReturn = ((double) lottoResultTotal / spentAmount) * 100;
        double percentOfReturnAfterRound = getSecondDecimalRound(percentOfReturn);

        return percentOfReturnAfterRound;
    }

    private double getSecondDecimalRound(double percentOfReturn) {
        return Math.round(percentOfReturn * 100) / 100.0;
    }
}


