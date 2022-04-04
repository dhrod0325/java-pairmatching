package pairmatching;

import pairmatching.model.PairInfo;
import pairmatching.reader.CrewReader;
import pairmatching.repository.InMemoryPairRepository;
import pairmatching.repository.PairRepository;
import pairmatching.service.PairService;

public class ApplicationContext {
    private static boolean running = true;

    private static final PairRepository repository = new InMemoryPairRepository();
    private static final PairInfo pairInfo = new PairInfo();
    private static final CrewReader crewReader = new CrewReader();
    private static final PairService pairService = new PairService();

    public static PairRepository getRepository() {
        return repository;
    }

    public static PairInfo getPairInfo() {
        return pairInfo;
    }

    public static CrewReader getCrewReader() {
        return crewReader;
    }

    public static PairService getPairService() {
        return pairService;
    }

    public static void shutdown() {
        repository.clear();
        pairInfo.clear();
        setRunning(false);
    }

    public static void setRunning(boolean running) {
        ApplicationContext.running = running;
    }

    public static boolean isRunning() {
        return ApplicationContext.running;
    }
}
