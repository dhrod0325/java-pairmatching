package pairmatching;

import pairmatching.runner.RunnerDispatcher;
import pairmatching.view.InputView;

public class Application {

    public static void main(String[] args) {
        while (ApplicationContext.isRunning()) {
            new RunnerDispatcher().dispatch(InputView.inputMenu());
        }
    }
}
