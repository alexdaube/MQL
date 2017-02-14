package domain.translators;

public class StateStatus {
    private final boolean done;
    private final QueryTranslatorState nextState;

    public StateStatus(boolean done, QueryTranslatorState nextState) {
        this.done = done;
        this.nextState = nextState;
    }

    public boolean isDone() {
        return done;
    }

    public QueryTranslatorState nextState() {
        return nextState;
    }
}
