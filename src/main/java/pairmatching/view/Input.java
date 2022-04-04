package pairmatching.view;

import pairmatching.constant.Course;
import pairmatching.constant.CourseLevel;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;

import java.util.List;

public class Input {
    private Course course;
    private Level level;
    private String mission;
    private CourseLevel courseLevel;

    private void parseCourse(String course) {
        this.course = Course.fromString(course);
    }

    private void parseLevel(String level) {
        this.level = Level.fromString(level);
    }

    private void parseMission(String mission) {
        List<String> missions = Mission.getMissionsByLevel(level);

        if (!missions.contains(mission)) {
            throw new IllegalArgumentException("[ERROR] 없는 미션입니다");
        }

        this.mission = mission;
    }

    public void parse(String text) {
        String[] texts = text.split(",");

        parseCourse(texts[0].trim());
        parseLevel(texts[1].trim());
        parseMission(texts[2].trim());

        this.courseLevel = new CourseLevel(course, level, mission);
    }

    public static Input fromText(String text) {
        Input pairInfo = new Input();
        pairInfo.parse(text);
        return pairInfo;
    }

    public Course getCourse() {
        return course;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }
}
