package pairmatching.repository;

import pairmatching.constant.CourseLevel;
import pairmatching.model.Crew;
import pairmatching.model.Pair;

import java.util.List;
import java.util.Map;

public interface PairRepository {
    void addPairList(CourseLevel courseLevel, List<Pair> pair);

    void addPair(CourseLevel courseLevel, Pair pair);

    void addAll(Map<CourseLevel, List<Pair>> pairData);

    Pair findPairByCrew(CourseLevel courseLevel, Crew crew);

    List<Pair> findList(CourseLevel courseLevel);

    void clear();

    void removePair(CourseLevel courseLevel);
}
