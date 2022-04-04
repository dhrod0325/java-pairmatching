package pairmatching.constant;

import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Level fromString(String name) {
        for (Level level : Level.values()) {
            if (level.name.equalsIgnoreCase(name)) {
                return level;
            }
        }
        return null;
    }

    public static List<Level> list() {
        return Arrays.asList(Level.LEVEL1, Level.LEVEL2, Level.LEVEL3, Level.LEVEL4, Level.LEVEL5);
    }
}
