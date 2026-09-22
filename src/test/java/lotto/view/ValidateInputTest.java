package lotto.view;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidateInputTest {
    @Test
    void 비어있는_입력값_예외() {
        String input = "";

        assertThatThrownBy(() -> ValidateInput.validateEmptyInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 비어있는 입력값입니다.");
    }

    @Nested
    class 로또_구입_금액_검증 {
        @Test
        void 로또_구입_금액이_양수가_아닐시_예외() {
            int input = -1;

            assertThatThrownBy(() -> ValidateInput.validatePositiveNumberInput(input))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 로또 구입 금액이 양수가 아닙니다.");
        }

        @Test
        void 로또_구입_금액이_1000_단위로_안_떨어질_경우_예외() {
            int input = 1100;

            assertThatThrownBy(() -> ValidateInput.validateMultipleOfThousandsInput(input))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 로또 구입 금액이 1000 단위가 아닙니다.");
        }
    }
}
