package pairmatching.runner.impl;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.ApplicationContext;
import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.reader.CrewReader;
import pairmatching.repository.PairRepository;
import pairmatching.runner.Runner;
import pairmatching.view.Input;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class MatchingRunner implements Runner {
    private static final int MAX_APPLY_COUNT = 3;

    private final PairRepository pairRepository = ApplicationContext.getRepository();
    private Input input;

    @Override
    public void run() {
        String pairInput = InputView.inputPairInfo();
        this.input = Input.fromText(pairInput);

        if (isAlreadyPair()) {
            String reply = InputView.inputReMatching();

            if (!"네".equalsIgnoreCase(reply)) {
                return;
            }
        }

        matching(MAX_APPLY_COUNT);
    }

    private boolean matching(int maxApplyCount) {
        if (maxApplyCount <= 0)
            return matchFail();

        List<String> shuffledCrewList = getShuffledCrewList();
        List<Pair> pairList = new ArrayList<>();

        int listSize = shuffledCrewList.size();

        for (int i = 0; i < listSize; i += 2) {
            if (i + 2 > listSize) break;

            Pair pair = createPair(shuffledCrewList, i);

            if (isRequireRematchingPair(pair))
                return matching(--maxApplyCount);

            pairList.add(pair);
        }

        return matchSuccess(pairList);
    }

    private boolean matchSuccess(List<Pair> pairList) {
        pairRepository.addPairList(input.getCourseLevel(), pairList);
        OutputView.printPairResult(pairList);
        return true;
    }

    private boolean matchFail() {
        throw new IllegalArgumentException(String.format("[ERROR] 재매칭 횟수 %d 초과로 실패", MAX_APPLY_COUNT));
    }

    private Pair createPair(List<String> crewList, int index) {
        int crewListSize = crewList.size();

        boolean isOdd = crewListSize % 2 != 0;

        Pair pair = new Pair(new Crew(crewList.get(index)), new Crew(crewList.get(index + 1)));

        if (isOdd && index == crewListSize - 3) {
            pair.addCrew(new Crew(crewList.get(index + 2)));
        }

        return pair;
    }

    private List<Pair> getPairList() {
        return pairRepository.findList(input.getCourseLevel());
    }

    private boolean isAlreadyPair() {
        return !getPairList().isEmpty();
    }

    private List<String> getShuffledCrewList() {
        List<String> readCrewList = CrewReader.getInstance().getCrewList(input.getCourse());
        return Randoms.shuffle(readCrewList);
    }

    private boolean isRequireRematchingPair(Pair pair) {
        for (Crew crew : pair.getCrewList()) {
            Pair existPair = pairRepository.findPairByCrew(input.getCourseLevel(), crew);
            return (existPair != null && !existPair.isMatchAble(crew, pair));
        }

        return false;
    }
}
