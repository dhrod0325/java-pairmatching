package pairmatching.constant;

import pairmatching.exception.PairException;

import java.util.*;

public class Mission {
    private static final Map<Level, List<String>> data = new HashMap<>();

    private Mission() {
        throw new PairException("객체를 생성할 수 없습니다");
    }

    static {
        data.put(Level.LEVEL1, Arrays.asList("자동차경주", "로또", "숫자야구게임"));
        data.put(Level.LEVEL2, Arrays.asList("장바구니", "결제", "지하철노선도"));
        data.put(Level.LEVEL3, Collections.emptyList());
        data.put(Level.LEVEL4, Arrays.asList("성능개선", "배포"));
        data.put(Level.LEVEL5, Collections.emptyList());
    }

    public static List<String> getNamesByLevel(Level level) {
        return Collections.unmodifiableList(data.get(level));
    }
}
