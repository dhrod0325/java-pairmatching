package pairmatching.view;

import pairmatching.model.Pair;

import java.util.List;

public class OutputView {
    public static void printPairResult(List<Pair> pairList) {
        pairList.forEach(pair -> System.out.println(pair.getCrewNames()));
    }
}
