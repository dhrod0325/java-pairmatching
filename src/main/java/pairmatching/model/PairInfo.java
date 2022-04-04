package pairmatching.model;

import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;

import java.util.List;

public class PairInfo {
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

    private void parseMission(String mission) throws IllegalArgumentException {
        List<String> missions = Mission.getMissionsByLevel(level);

        if (!missions.contains(mission)) {
            throw new IllegalArgumentException("[ERROR] 없는 미션입니다");
        }

        this.mission = mission;
    }

    public void parseFromText(String text) throws IllegalArgumentException {
        String[] texts = text.split(",");

        parseCourse(texts[0].trim());
        parseLevel(texts[1].trim());
        parseMission(texts[2].trim());

        this.courseLevel = new CourseLevel(course, level);
    }

    public Course getCourse() {
        return course;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    public void clear() {
        this.course = null;
        this.level = null;
        this.mission = null;
        this.courseLevel = null;
    }

    @Override
    public String toString() {
        return "PairInfo{" +
                "course=" + course +
                ", level=" + level +
                ", mission='" + mission + '\'' +
                ", courseLevel=" + courseLevel +
                '}';
    }
}
