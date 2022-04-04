package pairmatching.repository;

import pairmatching.constant.CourseLevel;
import pairmatching.model.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryPairRepository implements PairRepository {
    private final Map<CourseLevel, List<Pair>> pairData = new HashMap<>();

    @Override
    public void addPairList(CourseLevel courseLevel, List<Pair> pair) {
        pair.forEach(item -> addPair(courseLevel, item));
    }

    @Override
    public void addPair(CourseLevel courseLevel, Pair pair) {
        List<Pair> pairList = findPairList(courseLevel);
        pairList.add(pair);
        pairData.put(courseLevel, pairList);
    }

    @Override
    public List<Pair> findPairList(CourseLevel courseLevel) {
        return pairData.getOrDefault(courseLevel, new ArrayList<>());
    }

    @Override
    public void clear() {
        pairData.clear();
    }
}
