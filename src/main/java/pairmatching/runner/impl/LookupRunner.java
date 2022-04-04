package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.model.Pair;
import pairmatching.repository.PairRepository;
import pairmatching.runner.Runner;
import pairmatching.view.Input;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

public class LookupRunner implements Runner {
    private final PairRepository pairRepository = ApplicationContext.getRepository();

    @Override
    public void run() {
        String pairInput = InputView.inputPairInfo();
        Input pairInfo = Input.fromText(pairInput);

        List<Pair> pairList = pairRepository.findList(pairInfo.getCourseLevel());

        if (pairList.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 매칭 정보가 없습니다");
        }

        OutputView.printPairResult(pairList);
    }
}
