package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.exception.PairException;
import pairmatching.model.Input;
import pairmatching.model.Pair;
import pairmatching.repository.PairRepository;
import pairmatching.runner.Runner;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

public class LookupRunner implements Runner {
    private static final String ERROR_NOT_FOUND_MATCHING = "매칭 정보가 없습니다";

    private final PairRepository pairRepository = ApplicationContext.getRepository();

    @Override
    public void run() {
        String pairInput = InputView.inputPairInfo();
        Input pairInfo = Input.fromText(pairInput);

        List<Pair> pairList = pairRepository.findList(pairInfo.getCourseLevel());

        validate(pairList);

        OutputView.printPairResult(pairList);
    }

    private void validate(List<Pair> pairList) {
        if (pairList.isEmpty()) throw new PairException(ERROR_NOT_FOUND_MATCHING);
    }
}
