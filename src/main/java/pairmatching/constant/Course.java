package pairmatching.constant;

import java.util.Arrays;
import java.util.List;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course fromString(String name) {
        for (Course b : Course.values()) {
            if (b.name.equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }

    public static List<Course> list() {
        return Arrays.asList(Course.BACKEND, Course.FRONTEND);
    }
}
