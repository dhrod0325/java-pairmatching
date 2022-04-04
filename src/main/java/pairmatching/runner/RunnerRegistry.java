package pairmatching.runner;

import pairmatching.constant.Menu;
import pairmatching.runner.impl.InitRunner;
import pairmatching.runner.impl.LookupRunner;
import pairmatching.runner.impl.MatchingRunner;

import java.util.HashMap;
import java.util.Map;

public class RunnerRegistry {
    private final Map<Menu, Runner> runnerMap = new HashMap<>();

    public RunnerRegistry() {
        runnerMap.put(Menu.MATCHING, new MatchingRunner());
        runnerMap.put(Menu.LOOKUP, new LookupRunner());
        runnerMap.put(Menu.INIT, new InitRunner());
    }

    public Runner getRunner(Menu key) {
        return runnerMap.get(key);
    }
}
