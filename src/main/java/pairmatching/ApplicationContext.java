package pairmatching;

import pairmatching.repository.InMemoryPairRepository;
import pairmatching.repository.PairRepository;

public class ApplicationContext {
    private static final PairRepository repository = new InMemoryPairRepository();

    public static PairRepository getRepository() {
        return repository;
    }
}
