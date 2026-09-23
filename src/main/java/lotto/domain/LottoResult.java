package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> lottoResult;

    private LottoResult(Map<LottoRank, Integer> lottoResult) {
        this.lottoResult = lottoResult;
    }

    public static LottoResult createLottoResult() {
        Map<LottoRank, Integer> lottoResultFormat = new HashMap<>();

        for (LottoRank rank : LottoRank.values()) {
            lottoResultFormat.put(rank, 0);
        }

        return new LottoResult(lottoResultFormat);
    }

    public void plusRottoRankCount(LottoRank rank) {
        int beforeCount = lottoResult.get(rank);
        int afterCount = beforeCount + 1;
        lottoResult.put(rank, afterCount);
    }

    public int getLottoRankCount(LottoRank rank) {
        return lottoResult.get(rank);
    }

    public long getLottoResultTotal() {
        long total = 0L;
        for (LottoRank rank : LottoRank.values()) {
            int count = lottoResult.get(rank);
            int rankPrice = rank.getRankPrice();
            total += ((long) rankPrice * count);
        }

        return total;
    }
}
