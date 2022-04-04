package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.runner.Runner;
import pairmatching.view.OutputView;

public class InitRunner implements Runner {
    @Override
    public void run() {
        ApplicationContext.getRepository().clear();
        OutputView.out("초기화 되었습니다.");
    }
}
