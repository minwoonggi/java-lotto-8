package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {
    private LottoService lottoService = new LottoService();

    @Test
    void 로또_생성시_6자의_숫자를_반환_해야한다() {
        Lotto lotto = lottoService.getNewLotto();

        assertThat(lotto.getUnmodifiableNumbers()).hasSize(6);
    }
}
