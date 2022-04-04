package pairmatching.runner.impl;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.ApplicationContext;
import pairmatching.exception.PairException;
import pairmatching.input.MatchingInput;
import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.reader.CrewReader;
import pairmatching.repository.PairRepository;
import pairmatching.runner.Runner;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class MatchingRunner implements Runner {
    private static final int MAX_MATCHING_APPLY_COUNT = 3;
    private static final String ERROR_MATCHING_OVER_COUNT = String.format("재매칭 횟수 %d 초과로 실패", MAX_MATCHING_APPLY_COUNT);

    private final PairRepository repo = ApplicationContext.getRepository();
    private final MatchingInput input = new MatchingInput();

    @Override
    public void run() {
        input.parse(InputView.inputMatching());

        if (input.isAlreadyMatchingAndSkip())
            return;

        List<Pair> pairList = getMatchingPairList();

        savePairResult(pairList);

        OutputView.printPairResult(pairList);
    }

    private void savePairResult(List<Pair> pairList) {
        repo.addPairList(input.getCourseLevel(), pairList);
    }

    private List<Pair> getMatchingPairList() {
        return getMatchingPairList(0);
    }

    private List<Pair> getMatchingPairList(int applyCount) {
        validateMaxApplyCount(applyCount);

        List<Pair> pairList = new ArrayList<>();
        List<String> shuffledCrewList = getShuffledCrewList();

        for (int i = 0; i < shuffledCrewList.size(); i += 2) {
            if (i + 2 > shuffledCrewList.size()) break;

            Pair pair = createPair(shuffledCrewList, i);

            if (isRequireRematching(pair)) {
                return getMatchingPairList(++applyCount);
            }

            pairList.add(pair);
        }

        return pairList;
    }

    private void validateMaxApplyCount(int applyCount) {
        if (applyCount >= MAX_MATCHING_APPLY_COUNT) throw new PairException(ERROR_MATCHING_OVER_COUNT);
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

    private List<String> getShuffledCrewList() {
        return Randoms.shuffle(CrewReader.getInstance().getCrewList(input.getCourse()));
    }

    private boolean isRequireRematching(Pair pair) {
        List<Pair> list = repo.findPairList(input.getCourseLevel());

        for (Pair existPair : list) {
            if (!existPair.isMatchAble(pair)) {
                return true;
            }
        }

        return false;
    }
}
