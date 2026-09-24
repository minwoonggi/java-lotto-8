package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.ValidateInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static lotto.constant.LottoConstant.LOTTO_PRICE;

public class LottoController {
    private static final int CONTAIN_LAST_BLANK = -1;

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView,
                           OutputView outputView,
                           LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        int purchasePrice = retry(this::extractedPurchasePrice);
        int numberToPurchaseLotto = purchasePrice / LOTTO_PRICE;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberToPurchaseLotto; i++) {
            Lotto lotto = lottoService.getNewLotto();
            lottos.add(lotto);
            outputView.printLotto(lotto);
        }

        outputView.printLottoSize(numberToPurchaseLotto);
        List<Integer> winningNumbers = retry(this::extractedWinningNumbers);

        int bonusNumber = retry(() -> {
            return extractedBonusNumber(winningNumbers);
        });

        LottoResult lottoResult = lottoService.getLottoResult(lottos, winningNumbers, bonusNumber);
        outputView.printHitScore(lottoResult);

        double percentOfReturn = lottoService.getPercentOfReturn(lottoResult, purchasePrice);
        outputView.printPercentOfReturn(percentOfReturn);
    }

    private int extractedBonusNumber(List<Integer> winningNumbers) {
        String inputBonusNumber = inputView.inputBonusNumber();
        int bonusNumberInt = Integer.parseInt(inputBonusNumber);
        ValidateInput.validateBonusNumberRange(bonusNumberInt);
        ValidateInput.validateBonusNumberDuplicationWithWinningNumber(bonusNumberInt, winningNumbers);
        return bonusNumberInt;
    }

    private List<Integer> extractedWinningNumbers() {
        String inputWinningNumbers = inputView.inputWinningNumbers();
        List<Integer> winningNumberList = Arrays.stream(inputWinningNumbers.split(",", CONTAIN_LAST_BLANK))
                .map(Integer::parseInt)
                .toList();
        winningNumberList.forEach(ValidateInput::validateWinningNumbersRange);
        ValidateInput.validateWinningNumbersSize(winningNumberList);
        ValidateInput.validateWinningNumbersDuplication(winningNumberList);
        return winningNumberList;
    }

    private int extractedPurchasePrice() {
        String inputPurchasePrice = inputView.inputPurchasePrice();
        ValidateInput.validateEmptyInput(inputPurchasePrice);
        int inputPurchasePriceInt = Integer.parseInt(inputPurchasePrice);
        ValidateInput.validatePositiveNumberInput(inputPurchasePriceInt);
        ValidateInput.validateMultipleOfThousandsInput(inputPurchasePriceInt);
        return inputPurchasePriceInt;
    }

    private <T> T retry(Supplier<T> supplier) {
        while(true) {
            try {
                 return supplier.get();
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 입력값이 숫자가 아닙니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
