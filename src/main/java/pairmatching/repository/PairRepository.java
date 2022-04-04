package pairmatching.repository;

import pairmatching.constant.CourseLevel;
import pairmatching.model.Pair;

import java.util.List;

public interface PairRepository {
    void addPairList(CourseLevel courseLevel, List<Pair> pair);

    void addPair(CourseLevel courseLevel, Pair pair);

    List<Pair> findPairList(CourseLevel courseLevel);

    void clear();
}
