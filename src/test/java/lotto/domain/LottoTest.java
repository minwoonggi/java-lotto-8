package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @Test
    void 로또_숫자_오름차순_정렬() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));

        lotto.arrangeNumbers();

        assertThat(lotto.getUnmodifiableNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
