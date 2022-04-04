package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.runner.Runner;
import pairmatching.service.PairService;

public class LookupRunner implements Runner {
    private final PairService pairService = ApplicationContext.getPairService();

    @Override
    public void run() {
        try {
            pairService.setUpPairInfo();

            if (pairService.getPairList().isEmpty()) {
                throw new IllegalArgumentException("[ERROR] 매칭 정보가 없습니다");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        pairService.printMatching();
    }
}
