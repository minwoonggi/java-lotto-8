package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {
    private LottoService lottoService = new LottoService();

    @Test
    void 로또_생성시_6자의_숫자를_반환_해야한다() {
        Lotto lotto = lottoService.getNewLotto();

        assertThat(lotto.getUnmodifiableNumbers()).hasSize(6);
    }

    @Test
    void 로또_생성시_서로다른_숫자를_반환_해야한다() {
        Lotto lotto = lottoService.getNewLotto();

        assertThat(lotto.getUnmodifiableNumbers()).doesNotHaveDuplicates();
    }

    @Nested
    class 복수의_로또와_당첨_번호_보너스_번호_비교 {
        @Test
        void 로또가_3개_일치하는_경우() {
            List<Lotto> lottos = new ArrayList<>(List.of(
                    new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                    new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                    new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                    new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                    new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                    new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                    new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                    new Lotto(List.of(1, 3, 5, 14, 22, 45))
            ));
            List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
            int bonusNumber = 7;

            LottoResult lottoResult = lottoService.getLottoResult(lottos, winningNumbers, bonusNumber);

            assertThat(lottoResult.getLottoRankCount(LottoRank.FIFTH)).isEqualTo(1);
            assertThat(lottoResult.getLottoRankCount(LottoRank.FORTH)).isEqualTo(0);
            assertThat(lottoResult.getLottoRankCount(LottoRank.THIRD)).isEqualTo(0);
            assertThat(lottoResult.getLottoRankCount(LottoRank.SECOND)).isEqualTo(0);
            assertThat(lottoResult.getLottoRankCount(LottoRank.FIRST)).isEqualTo(0);
        }

        @Test
        void 로또가_5개_일치하고_보너스_일치할_경우() {
            List<Lotto> lottos = new ArrayList<>(List.of(
                    new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                    new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                    new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                    new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                    new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                    new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                    new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                    new Lotto(List.of(1, 3, 4, 5, 6, 7))
            ));
            List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
            int bonusNumber = 7;

            LottoResult lottoResult = lottoService.getLottoResult(lottos, winningNumbers, bonusNumber);

            assertThat(lottoResult.getLottoRankCount(LottoRank.FIFTH)).isEqualTo(0);
            assertThat(lottoResult.getLottoRankCount(LottoRank.FORTH)).isEqualTo(0);
            assertThat(lottoResult.getLottoRankCount(LottoRank.THIRD)).isEqualTo(0);
            assertThat(lottoResult.getLottoRankCount(LottoRank.SECOND)).isEqualTo(1);
            assertThat(lottoResult.getLottoRankCount(LottoRank.FIRST)).isEqualTo(0);
        }
    }

    @Test
    void 총_수익률을_계산한후_소수점_둘째_자리에서_반환한다() {
        LottoResult lottoResult = LottoResult.createLottoResult();
        lottoResult.plusRottoRankCount(LottoRank.FIFTH);
        int spentAmount = 8000;

        double percentOfReturn = lottoService.getPercentOfReturn(lottoResult, spentAmount);

        assertThat(percentOfReturn).isEqualTo(62.5);
    }
}
