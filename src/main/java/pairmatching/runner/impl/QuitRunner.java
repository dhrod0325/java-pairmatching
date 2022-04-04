package pairmatching.runner.impl;

import pairmatching.ApplicationContext;
import pairmatching.runner.Runner;

public class QuitRunner implements Runner {
    @Override
    public void run() {
        ApplicationContext.shutdown();
    }
}
