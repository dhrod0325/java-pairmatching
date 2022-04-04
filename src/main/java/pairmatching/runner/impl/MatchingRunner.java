package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.runner.Runner;
import pairmatching.service.PairService;
import pairmatching.view.InputView;

import java.util.List;

public class MatchingRunner implements Runner {
    private final PairService pairService = ApplicationContext.getPairService();

    @Override
    public void run() {
        try {
            pairService.setUpPairInfo();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (isStopMatching()) return;

        matching();

        pairService.printMatching();
    }

    private boolean isStopMatching() {
        List<Pair> pairList = pairService.getPairList();

        if (!pairList.isEmpty()) {
            String reply = InputView.inputReMatching();

            return !"네".equalsIgnoreCase(reply);
        }

        return false;
    }

    private void matching() {
        List<String> crewList = pairService.getCrewNames();
        int crewListSize = crewList.size();

        boolean isOdd = crewListSize % 2 != 0;

        for (int i = 0; i < crewListSize; i += 2) {
            if (i + 2 > crewListSize) break;

            Pair pair = new Pair(new Crew(crewList.get(i)), new Crew(crewList.get(i + 1)));

            if (isOdd && i == crewListSize - 3) {
                pair.addCrew(new Crew(crewList.get(i + 2)));
            }

            pairService.addPair(pair);
        }
    }
}
