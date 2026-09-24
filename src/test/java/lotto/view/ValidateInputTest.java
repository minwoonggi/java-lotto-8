package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    @DisplayName("당첨 번호는 1 ~ 45 사이의 숫자입니다.")
    void 당첨_번호_범위_예외() {
        int winningNumber = 46;

        assertThatThrownBy(() -> ValidateInput.validateWinningNumbersRange(winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 1 ~ 45 사이의 숫자 입니다.");
    }

    @Test
    void 당첨_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> ValidateInput.validateWinningNumbersDuplication(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 중복되면 안됩니다.");
    }

    @Test
    @DisplayName("보너스 번호는 1 ~ 45 사이의 숫자입니다.")
    void 보너스_번호_범위_예외() {
        int bonusNumber = 46;

        assertThatThrownBy(() -> ValidateInput.validateBonusNumberRange(bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1 ~ 45 사이의 숫자 입니다.");
    }

    @Test
    void 보너스_번호는_당첨_번호와_중복되면_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;

    assertThatThrownBy(() -> ValidateInput.validateBonusNumberDuplicationWithWinningNumber(bonusNumber, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호와 당첨번호 사이에 중복이 있습니다.");
    }
}
