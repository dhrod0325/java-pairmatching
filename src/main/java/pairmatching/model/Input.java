package pairmatching.model;

import pairmatching.constant.Course;
import pairmatching.constant.CourseLevel;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;
import pairmatching.exception.PairException;

import java.util.List;

public class Input {
    private static final String ERROR_COURSE = "없는 코스입니다";
    private static final String ERROR_LEVEL = "없는 레벨입니다";
    private static final String ERROR_MISSION = "없는 미션입니다";

    private Course course;
    private Level level;
    private String mission;
    private CourseLevel courseLevel;

    public Course getCourse() {
        return course;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    public static Input fromText(String text) {
        Input pairInfo = new Input();
        pairInfo.parse(text);
        return pairInfo;
    }

    private void parseCourse(String course) {
        this.course = Course.fromString(course);
        validate(this.course, Course.list(), ERROR_COURSE);
    }

    private void parseLevel(String level) {
        this.level = Level.fromString(level);
        validate(this.level, Level.list(), ERROR_LEVEL);
    }

    private void parseMission(String mission) {
        this.mission = mission;
        validate(this.mission, Mission.getNamesByLevel(level), ERROR_MISSION);
    }

    private <T> void validate(T check, List<T> list, String message) {
        if (!list.contains(check)) throw new PairException(message);
    }

    private void parse(String text) {
        String[] texts = text.split(",");

        parseCourse(texts[0].trim());
        parseLevel(texts[1].trim());
        parseMission(texts[2].trim());

        this.courseLevel = new CourseLevel(course, level, mission);
    }
}
