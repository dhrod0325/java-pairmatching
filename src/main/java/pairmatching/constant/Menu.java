package pairmatching.constant;

import java.util.Arrays;
import java.util.List;

public enum Menu {
    MATCHING("1"),
    LOOKUP("2"),
    INIT("3"),
    QUIT("Q");

    private final String name;

    Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<String> list() {
        return Arrays.asList(MATCHING.name, LOOKUP.name, INIT.name, QUIT.name);
    }
}
