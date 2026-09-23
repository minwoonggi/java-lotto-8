package lotto.domain;

import java.util.Optional;

public enum LottoRank {
    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FORTH(50_000, 4),
    FIFTH(5_000, 3);

    private int rankPrice;
    private int matchCount;

    LottoRank(int rankPrice, int matchCount) {
        this.rankPrice = rankPrice;
        this.matchCount = matchCount;
    }

    public int getRankPrice() {
        return rankPrice;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static Optional<LottoRank> getLottoRank(int matchCount,
                                        boolean matchWithBonusNumber) {
       for (LottoRank rank : LottoRank.values()) {
           if (rank.getMatchCount() == matchCount) {
               if (rank.getMatchCount() == 5 && matchWithBonusNumber) {
                   return Optional.of(SECOND);
               }
               else if (rank.getMatchCount() == 5 && !matchWithBonusNumber){
                   return Optional.of(THIRD);
               }
               return Optional.of(rank);
           }
       }
       return Optional.empty();
    }
}
