package pairmatching.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pair {
    private final List<Crew> crewList = new ArrayList<>();

    public Pair(Crew... crew) {
        crewList.addAll(Arrays.asList(crew));
    }

    public void addCrew(Crew crew) {
        if (crewList.contains(crew)) return;

        crewList.add(crew);
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    public String getCrewNames() {
        return crewList.stream()
                .map(Crew::getName)
                .collect(Collectors.joining(" : "));
    }
}
