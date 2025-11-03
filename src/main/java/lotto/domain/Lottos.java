package lotto.domain;

import java.util.List;
import java.util.stream.Stream;
import lotto.application.required.RandomNumberGenerator;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public static Lottos of(PurchasedAmount purchasedAmount, RandomNumberGenerator randomNumberGenerator) {
        int lottoCount = purchasedAmount.calculateLottoCount();

        List<Lotto> lottos = Stream.generate(() -> Lotto.createRandomLotto(randomNumberGenerator))
                .limit(lottoCount)
                .toList();

        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
