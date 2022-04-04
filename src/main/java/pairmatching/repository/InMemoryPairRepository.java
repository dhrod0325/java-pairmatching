package pairmatching.repository;

import pairmatching.model.CourseLevel;
import pairmatching.model.Crew;
import pairmatching.model.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryPairRepository implements PairRepository {
    private final Map<CourseLevel, List<Pair>> pairData = new HashMap<>();

    @Override
    public void addPair(CourseLevel courseLevel, Pair pair) {
        List<Pair> pairList = findPairListByCourseLevel(courseLevel);
        pairList.add(pair);
        pairData.put(courseLevel, pairList);
    }

    @Override
    public Pair findPairByCrew(CourseLevel courseLevel, Crew crew) {
        List<Pair> pairList = findPairListByCourseLevel(courseLevel);
        List<Pair> filteredList = pairList.stream()
                .filter(pair -> pair.getCrewList().contains(crew))
                .collect(Collectors.toList());

        if (!filteredList.isEmpty()) return filteredList.get(0);

        return null;
    }

    @Override
    public List<Pair> findPairListByCourseLevel(CourseLevel courseLevel) {
        return pairData.getOrDefault(courseLevel, new ArrayList<>());
    }

    @Override
    public void clear() {
        pairData.clear();
    }
}
