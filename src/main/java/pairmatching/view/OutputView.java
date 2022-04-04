package pairmatching.view;

import pairmatching.model.Pair;

import java.util.Comparator;
import java.util.List;

public class OutputView {
    public static void out(String msg) {
        System.out.println(msg);
    }

    public static void printPairResult(List<Pair> pairList) {
        pairList.sort(Comparator.comparing(Pair::getCrewNames));
        pairList.forEach(pair -> out(pair.getCrewNames()));
    }
}
