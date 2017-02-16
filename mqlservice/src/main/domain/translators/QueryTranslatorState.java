package domain.translators;

import domain.Query;

public interface QueryTranslatorState {
    StateStatus translate(Query query);
}
