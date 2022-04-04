package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.exception.PairException;
import pairmatching.input.Input;
import pairmatching.model.Pair;
import pairmatching.repository.PairRepository;
import pairmatching.runner.Runner;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

public class LookupRunner implements Runner {
    private static final String ERROR_NOT_FOUND_MATCHING = "매칭 정보가 없습니다";

    private final PairRepository pairRepository = ApplicationContext.getRepository();

    private final Input input = new Input();

    @Override
    public void run() {
        input.parse(InputView.inputMatching());

        List<Pair> pairList = pairRepository.findList(input.getCourseLevel());

        validate(pairList);

        OutputView.printPairResult(pairList);
    }

    private void validate(List<Pair> pairList) {
        if (pairList.isEmpty()) throw new PairException(ERROR_NOT_FOUND_MATCHING);
    }
}
