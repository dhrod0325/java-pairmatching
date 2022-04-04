package pairmatching.view;

import pairmatching.model.Pair;

import java.util.List;

public class OutputView {
    public static void out(String msg) {
        System.out.println(msg);
    }

    public static void printPairResult(List<Pair> pairList) {
        pairList.forEach(pair -> out(pair.getCrewNames()));
    }
}
