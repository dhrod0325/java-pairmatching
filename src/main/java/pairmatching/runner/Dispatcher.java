package pairmatching.runner;

import pairmatching.runner.impl.InitRunner;
import pairmatching.runner.impl.LookupRunner;
import pairmatching.runner.impl.MatchingRunner;
import pairmatching.view.InputView;

import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
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
                Runner runner = runnerMap.get(menu);
                runner.run();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
