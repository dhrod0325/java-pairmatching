package pairmatching.reader;

import pairmatching.constant.Course;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrewReader {
    private final Map<Course, List<String>> crewData = new HashMap<>();
    
    private final static String FRONTEND_FILE = "/frontend-crew.md";
    private final static String BACKEND_FILE = "/backend-crew.md";

    public CrewReader() {
        crewData.put(Course.BACKEND, getBackEndCrewList());
        crewData.put(Course.FRONTEND, getFrontEndCrewList());
    }

    public List<String> getCrewListByCourse(Course course) {
        return crewData.get(course);
    }

    private List<String> getFrontEndCrewList() {
        return readCrew(FRONTEND_FILE);
    }

    private List<String> getBackEndCrewList() {
        return readCrew(BACKEND_FILE);
    }

    private List<String> readCrew(String fileName) {
        try {
            URL resource = getClass().getResource(fileName);

            if (resource != null) {
                return Files.readAllLines(Paths.get(resource.getFile().substring(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
