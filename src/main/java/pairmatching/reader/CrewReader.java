package pairmatching.reader;

import pairmatching.constant.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrewReader {
    private final static FileReader fileReader = new FileReader();
    private final static CrewReader instance = new CrewReader();

    private final static String FRONTEND_FILE = "/frontend-crew.md";
    private final static String BACKEND_FILE = "/backend-crew.md";

    private final Map<Course, List<String>> cached = new HashMap<>();

    private CrewReader() {
        cached.put(Course.BACKEND, getBackEndCrewList());
        cached.put(Course.FRONTEND, getFrontEndCrewList());
    }

    public static CrewReader getInstance() {
        return instance;
    }

    public List<String> getCrewList(Course course) {
        return cached.get(course);
    }

    private List<String> getFrontEndCrewList() {
        return fileReader.getLines(FRONTEND_FILE);
    }

    private List<String> getBackEndCrewList() {
        return fileReader.getLines(BACKEND_FILE);
    }
}
