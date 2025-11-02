package lotto.application;

import java.util.List;
import java.util.stream.Stream;
import lotto.application.provided.LottoDispenser;
import lotto.application.required.RandomNumberGenerator;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;

public class LottoDispenserService implements LottoDispenser {
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoDispenserService(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public Lottos dispenseLottos(PurchaseAmount purchaseAmount) {
        int lottoCount = purchaseAmount.calculateLottoCount();

        List<Lotto> lottos = Stream.generate(() -> new Lotto(generateRandomNumbers()))
                .limit(lottoCount)
                .toList();

        return new Lottos(lottos);
    }

    private List<Integer> generateRandomNumbers() {
        return randomNumberGenerator.generateRandomNumbers(
                Lotto.getMinimumLottoNumber(),
                Lotto.getMaximumLottoNumber(),
                Lotto.getLottoSize()
        );
    }
}
