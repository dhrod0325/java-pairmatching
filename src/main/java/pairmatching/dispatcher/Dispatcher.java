package pairmatching.dispatcher;

import pairmatching.constant.Menu;
import pairmatching.exception.PairException;
import pairmatching.runner.Runner;
import pairmatching.runner.RunnerRegistry;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Dispatcher {
    private static final String ERROR_NOT_EXIST_MENU = "1,2,3,Q 중에 하나를 입력하세요";

    private final RunnerRegistry runnerRegistry = new RunnerRegistry();

    public void run() {
        while (true) {
            try {
                String inputMenu = InputView.inputMenu();

                Menu menu = Menu.fromString(inputMenu);

                validateMenu(inputMenu);

                if (isQuit(inputMenu))
                    return;

                Runner runner = runnerRegistry.getRunner(menu);
                runner.run();
            } catch (Exception e) {
                OutputView.out(e.getMessage());
            }
        }
    }

    private void validateMenu(String inputMenu) {
        if (!Menu.list().contains(inputMenu.toUpperCase()))
            throw new PairException(ERROR_NOT_EXIST_MENU);
    }

    private boolean isQuit(String inputMenu) {
        return inputMenu.equalsIgnoreCase("q");
    }
}
