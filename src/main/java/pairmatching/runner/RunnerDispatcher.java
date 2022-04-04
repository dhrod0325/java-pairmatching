package pairmatching.runner;

import pairmatching.runner.impl.InitRunner;
import pairmatching.runner.impl.LookupRunner;
import pairmatching.runner.impl.MatchingRunner;
import pairmatching.runner.impl.QuitRunner;

import java.util.HashMap;
import java.util.Map;

public class RunnerDispatcher {
    private final Map<String, Runner> runnerMap = new HashMap<>();

    public RunnerDispatcher() {
        runnerMap.put("1", new MatchingRunner());
        runnerMap.put("2", new LookupRunner());
        runnerMap.put("3", new InitRunner());
        runnerMap.put("Q", new QuitRunner());
    }

    public void dispatch(String type) {
        Runner runner = runnerMap.get(type.toUpperCase());

        if (runner != null) {
            runner.run();
        }
    }
}
