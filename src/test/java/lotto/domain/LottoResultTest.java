package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 로또_결과_정적_팩토리_메소드_생성시_모든_카운트값_0_초기화() {
        LottoResult lottoResult = LottoResult.createLottoResult();

        for (LottoRank rank : LottoRank.values()) {
            assertThat(lottoResult.getLottoRankCount(rank)).isZero();
        }
    }

    @Test
    void 로또_결과_당첨시_횟수가_1_증가() {
        LottoResult lottoResult = LottoResult.createLottoResult();

        for (LottoRank rank : LottoRank.values()) {
            lottoResult.plusRottoRankCount(rank);
            assertThat(lottoResult.getLottoRankCount(rank)).isEqualTo(1);
        }
    }

    @Test
    @DisplayName("1등과 2등이 당첨된 후 로또 금액 반환 테스트")
    void 로또_결과의_총합_반환() {
        LottoResult lottoResult = LottoResult.createLottoResult();

        lottoResult.plusRottoRankCount(LottoRank.FIRST);
        lottoResult.plusRottoRankCount(LottoRank.SECOND);

        assertThat(lottoResult.getLottoResultTotal()).isEqualTo(2_030_000_000L);
    }

}
