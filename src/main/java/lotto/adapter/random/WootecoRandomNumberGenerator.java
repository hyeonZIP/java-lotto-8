package lotto.adapter.random;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.application.required.RandomNumberGenerator;

public class WootecoRandomNumberGenerator implements RandomNumberGenerator {
    @Override
    public List<Integer> generateRandomNumbers(int start, int end, int size) {
        return Randoms.pickUniqueNumbersInRange(start, end, size);
    }
}
