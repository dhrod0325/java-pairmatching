package pairmatching.runner.impl;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.ApplicationContext;
import pairmatching.exception.PairException;
import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.reader.CrewReader;
import pairmatching.repository.PairRepository;
import pairmatching.runner.Runner;
import pairmatching.model.Input;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class MatchingRunner implements Runner {
    private static final int MAX_APPLY_COUNT = 3;
    private static final String ERROR_MATCHING_OVER_COUNT = String.format("재매칭 횟수 %d 초과로 실패", MAX_APPLY_COUNT);

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

    private void matching(int maxApplyCount) {
        if (maxApplyCount <= 0)
            throw new PairException(ERROR_MATCHING_OVER_COUNT);

        List<String> shuffledCrewList = getShuffledCrewList();
        List<Pair> pairList = new ArrayList<>();

        for (int i = 0; i < shuffledCrewList.size(); i += 2) {
            if (i + 2 > shuffledCrewList.size()) break;

            Pair pair = createPair(shuffledCrewList, i);

            if (isRequireRematchingPair(pair)) {
                matching(--maxApplyCount);
                return;
            }

            pairList.add(pair);
        }

        matchSuccess(pairList);
    }

    private void matchSuccess(List<Pair> pairList) {
        pairRepository.addPairList(input.getCourseLevel(), pairList);
        OutputView.printPairResult(pairList);
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
        return Randoms.shuffle(CrewReader.getInstance().getCrewList(input.getCourse()));
    }

    private boolean isRequireRematchingPair(Pair pair) {
        for (Crew crew : pair.getCrewList()) {
            Pair existPair = pairRepository.findPairByCrew(input.getCourseLevel(), crew);
            return (existPair != null && !existPair.isMatchAble(crew, pair));
        }

        return false;
    }
}
