package domain.query.translators;

import domain.query.Query;

public interface QueryTranslatorState {
    StateStatus translate(Query query);
}
