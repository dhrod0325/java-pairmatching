package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.repository.PairRepository;
import pairmatching.runner.Runner;
import pairmatching.view.OutputView;

public class InitRunner implements Runner {
    private final PairRepository repository = ApplicationContext.getRepository();

    @Override
    public void run() {
        repository.clear();
        OutputView.out("초기화 되었습니다.");
    }
}
