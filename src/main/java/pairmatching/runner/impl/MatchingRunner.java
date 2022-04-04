package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.reader.CrewReader;
import pairmatching.repository.PairRepository;
import pairmatching.runner.Runner;
import pairmatching.view.Input;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

public class MatchingRunner implements Runner {
    private final PairRepository pairRepository = ApplicationContext.getRepository();

    @Override
    public void run() {
        String pairInput = InputView.inputPairInfo();
        Input pairInfo = Input.fromText(pairInput);

        if (isAlreadyPair(pairInfo)) {
            String reply = InputView.inputReMatching();

            if (!"네".equalsIgnoreCase(reply)) {
                return;
            }
        }

        matching(pairInfo);

        List<Pair> pairList = pairRepository.findList(pairInfo.getCourseLevel());
        OutputView.printPairResult(pairList);
    }

    private boolean isAlreadyPair(Input pairInfo) {
        List<Pair> pairList = pairRepository.findList(pairInfo.getCourseLevel());
        return !pairList.isEmpty();
    }

    private void matching(Input pairInfo) {
        List<String> crewList = new CrewReader().getCrewList(pairInfo.getCourse());
        int crewListSize = crewList.size();

        boolean isOdd = crewListSize % 2 != 0;

        for (int i = 0; i < crewListSize; i += 2) {
            if (i + 2 > crewListSize) break;

            Pair pair = new Pair(new Crew(crewList.get(i)), new Crew(crewList.get(i + 1)));

            if (isOdd && i == crewListSize - 3) {
                pair.addCrew(new Crew(crewList.get(i + 2)));
            }

            pairRepository.addPair(pairInfo.getCourseLevel(), pair);
        }
    }
}
