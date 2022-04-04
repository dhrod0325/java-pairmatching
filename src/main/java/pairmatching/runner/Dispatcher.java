package pairmatching.runner;

import pairmatching.constant.Menu;
import pairmatching.exception.PairException;
import pairmatching.runner.impl.InitRunner;
import pairmatching.runner.impl.LookupRunner;
import pairmatching.runner.impl.MatchingRunner;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private static final String ERROR_NOT_EXIST_MENU = "1,2,3,Q 중에 하나를 입력하세요";

    private final Map<Menu, Runner> runnerMap = new HashMap<>();

    public Dispatcher() {
        runnerMap.put(Menu.MATCHING, new MatchingRunner());
        runnerMap.put(Menu.LOOKUP, new LookupRunner());
        runnerMap.put(Menu.INIT, new InitRunner());
    }

    public void run() {
        while (true) {
            try {
                String inputMenu = InputView.inputMenu();

                Menu menu = Menu.valueOf(inputMenu);

                validateMenu(inputMenu);

                if (isQuit(inputMenu)) return;

                Runner runner = runnerMap.get(menu);
                runner.run();
            } catch (Exception e) {
                OutputView.out(e.getMessage());
            }
        }
    }

    private void validateMenu(String inputMenu) {
        if (!Menu.list().contains(inputMenu))
            throw new PairException(ERROR_NOT_EXIST_MENU);
    }

    private boolean isQuit(String inputMenu) {
        return inputMenu.equalsIgnoreCase("q");
    }
}
