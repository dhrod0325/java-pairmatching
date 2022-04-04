package pairmatching.repository;

import pairmatching.constant.CourseLevel;
import pairmatching.model.Crew;
import pairmatching.model.Pair;

import java.util.List;

public interface PairRepository {
    void addPair(CourseLevel courseLevel, Pair pair);

    Pair findPairByCrew(CourseLevel courseLevel, Crew crew);

    List<Pair> findList(CourseLevel courseLevel);

    void clear();
}
