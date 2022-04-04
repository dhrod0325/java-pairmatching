package pairmatching.service;

import pairmatching.ApplicationContext;
import pairmatching.model.Pair;
import pairmatching.model.PairInfo;
import pairmatching.reader.CrewReader;
import pairmatching.repository.PairRepository;
import pairmatching.view.InputView;

import java.util.List;

public class PairService {
    protected final PairInfo pairInfo = ApplicationContext.getPairInfo();
    protected final PairRepository pairRepository = ApplicationContext.getRepository();
    protected final CrewReader crewReader = ApplicationContext.getCrewReader();

    public void printMatching() {
        List<Pair> pairList = pairRepository.findPairListByCourseLevel(pairInfo.getCourseLevel());
        pairList.forEach(pair -> System.out.println(pair.getCrewNames()));
    }

    public void setUpPairInfo() throws IllegalArgumentException {
        String pairInput = InputView.inputPairInfo();

        System.out.println(pairInfo);

        pairInfo.parseFromText(pairInput);
    }

    public List<Pair> getPairList() {
        return pairRepository.findPairListByCourseLevel(pairInfo.getCourseLevel());
    }

    public List<String> getCrewNames() {
        return crewReader.getCrewListByCourse(pairInfo.getCourse());
    }

    public void addPair(Pair pair) {
        pairRepository.addPair(pairInfo.getCourseLevel(), pair);
    }
}
