package pairmatching.constant;

import java.util.Objects;

public class CourseLevel {
    private final Course course;
    private final Level level;

    public CourseLevel(Course course, Level level) {
        this.course = course;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseLevel that = (CourseLevel) o;
        return course == that.course && level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level);
    }
}
