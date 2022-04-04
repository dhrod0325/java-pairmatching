package pairmatching.runner;

import pairmatching.exception.PairException;
import pairmatching.runner.impl.InitRunner;
import pairmatching.runner.impl.LookupRunner;
import pairmatching.runner.impl.MatchingRunner;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private static final String ERROR_KEY = "1,2,3,Q 중에 하나를 입력하세요";

    private final Map<String, Runner> runnerMap = new HashMap<>();

    public Dispatcher() {
        runnerMap.put("1", new MatchingRunner());
        runnerMap.put("2", new LookupRunner());
        runnerMap.put("3", new InitRunner());
    }

    public void run() {
        while (true) {
            String menu = InputView.inputMenu();

            if (menu.equalsIgnoreCase("q")) return;

            try {
                if (!runnerMap.containsKey(menu)) {
                    throw new PairException(ERROR_KEY);
                }

                Runner runner = runnerMap.get(menu);
                runner.run();
            } catch (Exception e) {
                OutputView.out(e.getMessage());
            }
        }
    }
}
