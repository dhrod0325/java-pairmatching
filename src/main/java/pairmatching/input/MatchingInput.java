package pairmatching.input;

import pairmatching.ApplicationContext;
import pairmatching.repository.PairRepository;
import pairmatching.view.InputView;

public class MatchingInput extends Input {
    private final PairRepository repo = ApplicationContext.getRepository();

    public boolean isAlreadyMatchingAndSkip() {
        return isAlreadyMatching() && isSkipMatching();
    }

    private boolean isAlreadyMatching() {
        return !repo.findList(getCourseLevel()).isEmpty();
    }

    private boolean isSkipMatching() {
        String inputReMatching = InputView.inputReMatching();
        return !isRematching(inputReMatching);
    }

    private boolean isRematching(String reply) {
        return "네".equalsIgnoreCase(reply);
    }
}
