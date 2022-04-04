package pairmatching.exception;

public class PairException extends IllegalArgumentException {
    public PairException(String msg) {
        super("[ERROR] " + msg);
    }
}
