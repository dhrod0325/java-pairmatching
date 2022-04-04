package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.runner.Runner;

public class InitRunner implements Runner {
    @Override
    public void run() {
        ApplicationContext.getRepository().clear();
        System.out.println("초기화 되었습니다.");
    }
}
