package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;

public class OutputView {
    public void printLottoSize(int numberToPurchaseLotto) {
        System.out.println(numberToPurchaseLotto + "개를 구매했습니다.");
    }

    public void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public void printHitScore(LottoResult lottoResult) {
        System.out.println("3개 일치 (5,000원) - " + lottoResult.getLottoRankCount(LottoRank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + lottoResult.getLottoRankCount(LottoRank.FORTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + lottoResult.getLottoRankCount(LottoRank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + lottoResult.getLottoRankCount(LottoRank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + lottoResult.getLottoRankCount(LottoRank.FIRST) + "개");
    }

    public void printPercentOfReturn(double percentOfReturn) {
        System.out.printf("총 수익률은 %,.1f%%입니다.", percentOfReturn);
    }
}
